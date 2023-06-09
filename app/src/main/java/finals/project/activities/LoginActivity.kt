package finals.project.activities

import finals.project.databinding.ActivityLoginBinding
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.ViewModelProvider
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.ktx.auth
import finals.project.data.DataActivity
import com.google.firebase.ktx.Firebase
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import android.text.TextWatcher
import android.widget.EditText
import android.content.Intent
import android.text.Editable
import android.widget.Toast
import java.lang.Exception
import android.os.Bundle
import android.view.View
import finals.project.R
import finals.project.ui.login.LoginViewModel
import finals.project.ui.login.LoginViewModelFactory

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

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

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
                    Thread.sleep(1000)
                    loading.visibility = View.VISIBLE
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val uid = uidGrab()
                val displayName = nameGrab()
                val name = displayName?.let { it1 -> emailTrim(it1) }
                if (uid != null) {
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        Toast.makeText(
                            applicationContext,
                            "Successful Login for User: $name",
                            Toast.LENGTH_LONG
                        ).show()
                        DataActivity.dataBase(uid, name, displayName)
                        startActivity(intent)
                    } else {
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        Toast.makeText(
                            applicationContext,
                            "Email verification link sent to $displayName",
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

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun isReachable(): Any {
            return true
        }

        fun emailTrim(email: String): String {
            val name = email.substring(0, email.indexOf("@"))
            name.trim()
            return name
        }

        fun nameGrab(): String? {
            return Firebase.auth.currentUser?.email
        }

        fun uidGrab(): String? {
            return Firebase.auth.currentUser?.uid
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

    }
}



