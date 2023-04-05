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
import finals.project.smsPage.LatestMessagesActivity


class FriendSearchActivity : AppCompatActivity() {
    lateinit var searchView: SearchView
    lateinit var listView: ListView
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
        val contactInfo = findViewById<View>(finals.project.R.id.layout)
        contactInfo.visibility = View.GONE
        profileTitle.visibility = View.GONE

        val mAuth = FirebaseAuth.getInstance()
        val currentUserId = mAuth.currentUser?.uid
        val profileUserRef = FirebaseDatabase.getInstance().getReference()
        System.out.println("DISJDIOJASOIJDOASD" + profileUserRef)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
// do something on text submit
                val query = searchView.getQuery()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
// do something when text changes
                val query = searchView.getQuery()
                profileUserRef.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val email = dataSnapshot.child("Email").child(query.toString()).getValue().toString()
                            System.out.println("DSIAJDIOASJOIDJIASJDISAJD " + email)
                            val name = dataSnapshot.child("Name").getValue().toString()
                            //list.add(name)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })
                return false
            }
        })

        listView = findViewById(finals.project.R.id.listView)
        listView.visibility = View.VISIBLE


        val database = FirebaseDatabase.getInstance()
        val myRefEmail = database.getReference("users/")

        list = ArrayList()

        list.add("Apple")
        list.add("Banana")
        list.add("Pineapple")
        list.add("Orange")
        list.add("Mango")
        list.add("Grapes")
        list.add("Lemon")
        list.add("Melon")
        list.add("Watermelon")
        list.add("Papaya")


        adapter = ArrayAdapter<String>(applicationContext, finals.project.R.layout.text_color_layout, list)
        listView.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                myRefEmail.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val email = dataSnapshot.child(query).child("Email").getValue().toString()
                            System.out.println("DSIAJDIOASJOIDJIASJDISAJD " + email)
                            val name = dataSnapshot.child("Name").getValue().toString()
                            //list.add(name)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
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