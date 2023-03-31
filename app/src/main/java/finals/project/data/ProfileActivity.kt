package finals.project.data

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import finals.project.databinding.ActivityLoginBinding
import finals.project.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_profile)
        val intentLogin = Intent(this, LoginActivity::class.java)
        val intentReturn = Intent(this, HomeActivity::class.java)
        val displayName = LoginActivity.nameGrab()
        val name = displayName?.let { LoginActivity.emailTrim(it) }
        val uid = LoginActivity.uidGrab()
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        profileTitle.text = "Welcome " + name + "!"

        val email = findViewById<View>(finals.project.R.id.email) as TextView
        email.text = "Email: \n" + displayName

        //this will be a way to add people as friends
        val userID = findViewById<View>(finals.project.R.id.user) as TextView
        userID.text = "User ID: \n" + uid

        val updatePassButton = findViewById<View>(finals.project.R.id.update)
        updatePassButton.setOnClickListener {
            if (displayName != null) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(displayName)
                Toast.makeText(
                    applicationContext,
                    "Email sent to " + displayName,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        val returnButton = findViewById<View>(finals.project.R.id.home)
        returnButton.setOnClickListener {
            startActivity(intentReturn)
        }

        val soButton = findViewById<View>(finals.project.R.id.so)
        soButton.setOnClickListener {
            startActivity(intentLogin)
            FirebaseAuth.getInstance().signOut()
        }
    }
}