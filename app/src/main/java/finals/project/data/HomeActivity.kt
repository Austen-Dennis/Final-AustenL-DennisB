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
        setContentView(finals.project.R.layout.homepage) // sets the view using the homepage.xml
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)

        //sets the intent I.E. calls to the class Smspage which displays a layout
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val intentSEARCH = Intent(this, FriendSearchActivity::class.java)


        //starts the activity onclick
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


    //only for testing.
    companion object {
        fun isReachable(): Any {
            val reachable = true;
            return reachable
        }
    }
}