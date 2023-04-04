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


class FriendSearchActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_friend_search) // sets the view using the homepage.xml
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        //sets the intent I.E. calls to the class Smspage which displays a layout
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val searchButton = findViewById<View>(finals.project.R.id.imageView2)
        val searchView = findViewById<View>(finals.project.R.id.searchView) as EditText
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val contactInfo = findViewById<View>(finals.project.R.id.layout)
        contactInfo.visibility = View.GONE
        profileTitle.visibility = View.GONE
        searchButton.setOnClickListener {
            val text = searchView.text
            friendSearch(text.toString())
            try {
                Thread.sleep(200);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

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
        val homeButton = findViewById<View>(finals.project.R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }
    }
    private fun friendSearch(uid: String) {
        val database = FirebaseDatabase.getInstance()
        val myRefEmail = database.getReference("users").child(uid).child("Name",)
        myRefEmail.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
                val contactInfo = findViewById<View>(finals.project.R.id.layout)
                contactInfo.visibility = View.VISIBLE
                profileTitle.text = "Now Viewing " + value + "'s Account!"
                profileTitle.visibility = View.VISIBLE

                //Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

    }

    //only for testing.
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
    }
}