package finals.project.data

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import finals.project.smsPage.SmsPage
import finals.project.ui.login.LoginActivity
import java.util.logging.Level.parse

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_profile)
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        val intentLogin = Intent(this, LoginActivity::class.java)
        val intentPost = Intent(this, PostActivity::class.java)
        val intentSms = Intent(this, SmsPage::class.java)
        val intentReturn = Intent(this, HomeActivity::class.java)
        val urlRelease = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR/releases"
        val releaseIntent = Intent(Intent.ACTION_VIEW)
        val urlProjectInfo = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR#get-together"
        val projectInfoIntent = Intent(Intent.ACTION_VIEW)
        val displayName = LoginActivity.nameGrab()
        val name = displayName?.let { LoginActivity.emailTrim(it) }
        val uid = LoginActivity.uidGrab()
        val clip: ClipData = ClipData.newPlainText("simple text", uid)
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val hideInfoButton = findViewById<View>(finals.project.R.id.hideInfo)
        val showInfoButton = findViewById<View>(finals.project.R.id.displayInfo)
        val copyIDButton = findViewById<View>(finals.project.R.id.copyID)

        profileTitle.text = "Welcome " + name + "!"

        val email = findViewById<View>(finals.project.R.id.email) as TextView
        email.text = "Email: \n" + displayName

        //this will be a way to add people as friends
        val userID = findViewById<View>(finals.project.R.id.user) as TextView
        userID.text = "User ID: \n" + uid

        email.visibility= View.GONE
        userID.visibility=View.GONE
        hideInfoButton.visibility=View.GONE
        copyIDButton.visibility=View.GONE



        showInfoButton.setOnClickListener {
            email.visibility= View.VISIBLE
            userID.visibility=View.VISIBLE
            hideInfoButton.visibility=View.VISIBLE
            showInfoButton.visibility=View.GONE
            copyIDButton.visibility=View.VISIBLE
        }
        hideInfoButton.setOnClickListener {
            email.visibility= View.GONE
            userID.visibility=View.GONE
            showInfoButton.visibility=View.VISIBLE
            hideInfoButton.visibility=View.GONE
            copyIDButton.visibility=View.GONE
        }
        copyIDButton.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("simple text", uid)
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
            Toast.makeText(
                applicationContext,
                "Successfully Copied User ID",
                Toast.LENGTH_LONG
            ).show()
        }
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
        val releaseButton = findViewById<View>(finals.project.R.id.release)
        releaseButton.setOnClickListener {
            releaseIntent.data = Uri.parse(urlRelease)
            startActivity(releaseIntent)
        }
        val projectInfoButton = findViewById<View>(finals.project.R.id.project)
        projectInfoButton.setOnClickListener {
            projectInfoIntent.data = Uri.parse(urlProjectInfo)
            startActivity(projectInfoIntent)

        }

        val postButton = findViewById<View>(finals.project.R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPost)
        }

        val smsButton = findViewById<View>(finals.project.R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSms)
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