package finals.project.data

import android.R
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import finals.project.smsPage.LatestMessagesActivity
import finals.project.smsPage.SmsPage
import java.lang.Exception


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
            //val text = searchView.text
            //.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER)
            //System.out.println("DSIAJDIASJDISAJDAS " + text)
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