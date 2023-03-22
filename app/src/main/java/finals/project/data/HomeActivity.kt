package finals.project.data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.UserInfo
import finals.project.R
import finals.project.data.model.ProfileActivity
import finals.project.data.model.UserInformation
import finals.project.smsPage.SmsPage

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserInformation()
        setContentView(R.layout.homepage)
        val intentSMS = Intent(this, SmsPage::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        val smsButton = findViewById<View>(R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val postButton = findViewById<View>(R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPOST)
        }
    }

    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
    }
}