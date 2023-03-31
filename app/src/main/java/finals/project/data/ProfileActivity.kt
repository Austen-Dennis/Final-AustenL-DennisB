package finals.project.data

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import finals.project.ui.login.LoginActivity


class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_profile)
        val intentLogin = Intent(this, LoginActivity::class.java)
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

        val soButton = findViewById<View>(finals.project.R.id.so)
        soButton.setOnClickListener {
            startActivity(intentLogin)
            FirebaseAuth.getInstance().signOut()
        }
    }
}