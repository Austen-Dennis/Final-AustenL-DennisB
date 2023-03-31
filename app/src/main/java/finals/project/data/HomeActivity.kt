package finals.project.data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import finals.project.R
import finals.project.smsPage.SmsPage
import finals.project.ui.login.LoginActivity
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.homepage) // sets the view using the homepage.xml
        // sets the intent I.E. calls to the class Smspage which displays a layout
        val intentSMS = Intent(this, SmsPage::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        //starts the activity onclick
        val profileButton = findViewById<View>(R.id.profile)
        profileButton.setOnClickListener {
            startActivity(intentPROFILE)
        }
        val smsButton = findViewById<View>(R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val postButton = findViewById<View>(R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPOST)
        }
    }

    //only for testing.
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
    }
}