package finals.project.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import finals.project.R
import finals.project.smsPage.SmsPage

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        val intentSMS = Intent(this, SmsPage::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val intentCAMERA = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val smsButton = findViewById<View>(R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val homeButton = findViewById<View>(R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }
        val cameraButton = findViewById<View>(R.id.Camera)
        cameraButton.setOnClickListener {
            startActivity(intentCAMERA)
        }
    }
}