package finals.project.data

import finals.project.smsPage.LatestMessagesActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.os.Bundle

class HomeActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets view as homepage.xml
        setContentView(finals.project.R.layout.homepage)

        //sets intents and toolbar values
        val intentSEARCH = Intent(this, FriendSearchActivity::class.java)
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        setSupportActionBar(toolbar as Toolbar?)

        //starts the activities onclick
        val profileButton = findViewById<View>(finals.project.R.id.profile)
        profileButton.setOnClickListener {
            startActivity(intentPROFILE)
        }
        val smsButton = findViewById<View>(finals.project.R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val postButton = findViewById<View>(finals.project.R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPOST)
        }
        val searchButton = findViewById<View>(finals.project.R.id.search)
        searchButton.setOnClickListener {
            startActivity(intentSEARCH)
        }
    }


    //verifies that companion object can be reached
    companion object {
        fun isReachable(): Any {
            val reachable = true;
            return reachable
        }
    }
}