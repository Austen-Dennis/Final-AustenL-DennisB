package finals.project.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import finals.project.smsPage.LatestMessagesActivity


class FriendSearchActivity : AppCompatActivity() {
    lateinit var searchView: SearchView
    lateinit var list: ArrayList<String>
    lateinit var adapter: ArrayAdapter<*>
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
        val searchView = findViewById<View>(finals.project.R.id.searchView) as SearchView
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val layout = findViewById<View>(finals.project.R.id.layout)
        val addFriend = findViewById<View>(finals.project.R.id.addFriend)
        val uid = FirebaseAuth.getInstance().uid
        val emailValue = findViewById<View>(finals.project.R.id.emailValue) as TextView
        val bioValue = findViewById<View>(finals.project.R.id.bioValue) as TextView
        val nameValue = findViewById<View>(finals.project.R.id.nameValue) as TextView
        val gitValue = findViewById<View>(finals.project.R.id.gitValue) as TextView
        val myRef = FirebaseDatabase.getInstance().getReference("users")
        layout.visibility=View.GONE


        val mAuth = FirebaseAuth.getInstance()
        val currentUserId = mAuth.currentUser?.uid
        val profileUserRef = FirebaseDatabase.getInstance().getReference()

        val database = FirebaseDatabase.getInstance()
        val myRefEmail = database.getReference("users/")
        val myEmail = FirebaseAuth.getInstance().currentUser?.email

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                myRefEmail.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            layout.visibility=View.VISIBLE
                            val email = dataSnapshot.child(query).child("Email").getValue().toString()
                            val name = dataSnapshot.child(query).child("Name").getValue().toString()
                            val bioSnap = dataSnapshot.child(query).child("Bio").getValue().toString()
                            val gitLink = dataSnapshot.child(query).child("GitHub Link").getValue().toString()
                            val collegeEmailSnap = dataSnapshot.child(query).child("College Email").getValue().toString()
                            profileTitle.text = "Profile Search"
                            if (dataSnapshot.child(query.toString()).child("Name").exists()) {
                                nameValue.text = name
                            } else {
                                nameValue.text = email
                            }
                            if (dataSnapshot.child(query.toString()).child("Bio").exists()) {
                                bioValue.text = bioSnap
                            } else {
                                bioValue.text = "No Bio Yet"
                            }
                            if (dataSnapshot.child(query.toString()).child("GitHub Link").exists()) {
                                gitValue.text = "GitHub Link: " + gitLink
                            } else {
                                gitValue.text = "No GitHub Link Yet"
                            }
                            if (dataSnapshot.child(query.toString()).child("College Email").exists()) {
                                emailValue.text = "Contact: " + collegeEmailSnap
                            } else {
                                emailValue.text = "Contact: " + email
                            }
                            addFriend.setOnClickListener {
                                myRefEmail.child(query).child("Pending Friend Request").push().setValue(myEmail)
                                Toast.makeText(
                                    applicationContext,
                                    "Friend Request Sent!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
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

    //only for testing.
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
    }
}