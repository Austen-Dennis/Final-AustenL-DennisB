package finals.project.ui.login

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import finals.project.R
import finals.project.data.DataActivity
import finals.project.data.HomeActivity
import finals.project.databinding.ActivityLoginBinding
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)

        //Verifies that app terminates previous users session
        FirebaseAuth.getInstance().signOut()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val intent = Intent(this, HomeActivity::class.java)


        val verifyButton = findViewById<View>(R.id.verify)
        verifyButton.setOnClickListener {
            verifyButton.visibility = View.INVISIBLE
            username.visibility = View.VISIBLE
            password.visibility = View.VISIBLE
            login.visibility = View.VISIBLE
        }

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }

        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                //updateUiWithUser(loginResult.success)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginViewModel.login(username.text.toString(), password.text.toString())
                try {
                    Thread.sleep(1000);
                    loading.visibility = View.VISIBLE
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val uid = uidGrab()
                val displayName = nameGrab()
                val name = displayName?.let { it1 -> emailTrim(it1) }
                val user = displayName?.let { it1 -> uid }
                if (uid != null) {
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        Toast.makeText(
                            applicationContext,
                            "Successful Login for User: " + name,
                            Toast.LENGTH_LONG
                        ).show()
                        DataActivity.dataBase(uid, name, displayName)
                        startActivity(intent)
                    } else {
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        Toast.makeText(
                            applicationContext,
                            "Email verification link sent to " + displayName,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    loading.visibility = View.INVISIBLE
                    Toast.makeText(
                        applicationContext,
                        "Incorrect Password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun updateUiWithUser(model: LoggedInUserView) {
        try {
            Thread.sleep(1000);
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun iscreated(): Any {
            val created = true
            return created
        }

        fun emailTrim(email: String): String? {
            val name = email?.substring(0, email.indexOf("@"))
            name?.trim()
            return name
        }

        fun nameGrab(): String? {
            var displayName = Firebase.auth.currentUser?.email
            return displayName
        }

        fun uidGrab(): String? {
            var uid = Firebase.auth.currentUser?.uid
            return uid
        }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

    }
}



