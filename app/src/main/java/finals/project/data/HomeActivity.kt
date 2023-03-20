package finals.project.data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import finals.project.R
import finals.project.smsPage.MainActivity

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val intentSMS = Intent(this, MainActivity::class.java)
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
}