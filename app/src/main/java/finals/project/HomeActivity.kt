package finals.project

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.widget.Toolbar
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.view.View
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import finals.project.data.Friends
import finals.project.data.ProfileActivity
import finals.project.smsPage.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    companion object {
        const val USER_KEY = "USER_KEY"
        const val ID = "ID"

        fun isReachable(): Any {
            return true
        }

    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // sets the view using the activity_friend_search
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)

        //sets the intent I.E. calls to the class LatestMessages which displays a layout
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val searchView = findViewById<View>(finals.project.R.id.searchView) as SearchView
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val layout = findViewById<View>(R.id.layout1)
        val layout2 = findViewById<View>(finals.project.R.id.layout2)
        val addFriend = findViewById<View>(finals.project.R.id.addFriend)
        val uid = FirebaseAuth.getInstance().uid
        val emailValue = findViewById<View>(finals.project.R.id.emailValue) as TextView
        val bioValue = findViewById<View>(finals.project.R.id.bioValue) as TextView
        val nameValue = findViewById<View>(finals.project.R.id.nameValue) as TextView
        val gitValue = findViewById<View>(finals.project.R.id.gitValue) as TextView
        val messageButton = findViewById<View>(R.id.messageFriend)

        layout.visibility = View.VISIBLE
        layout2.visibility = View.GONE

        fetchUsers()

        val mAuth = FirebaseAuth.getInstance()
        val currentUserId = mAuth.currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val myRefEmail = database.getReference("users/")
        val chatIntent = Intent(this, ChatLogActivity::class.java)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                myRefEmail.addValueEventListener(object : ValueEventListener {

                    @SuppressLint("SetTextI18n")
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        if (dataSnapshot.exists()) {

                            if (dataSnapshot.child(query).exists() && query != currentUserId) {
                                layout2.visibility = View.VISIBLE
                                layout.visibility = View.GONE

                                val email =
                                    dataSnapshot.child(query).child("Email").value.toString()
                                val name =
                                    dataSnapshot.child(query).child("Name").value.toString()
                                val bioSnap =
                                    dataSnapshot.child(query).child("Bio").value.toString()
                                val gitLink =
                                    dataSnapshot.child(query).child("GitHub Link").value
                                        .toString()
                                val collegeEmailSnap =
                                    dataSnapshot.child(query).child("College Email").value
                                        .toString()

                                profileTitle.text = "Profile Search"

                                if (dataSnapshot.child(query).child("Name").exists()) {
                                    nameValue.text = name
                                } else {
                                    nameValue.text = email
                                }
                                if (dataSnapshot.child(query).child("Bio").exists()) {
                                    bioValue.text = bioSnap
                                } else {
                                    bioValue.text = "No Bio Yet"
                                }
                                if (dataSnapshot.child(query).child("GitHub Link")
                                        .exists()
                                ) {
                                    gitValue.text = "GitHub Link: $gitLink"
                                } else {
                                    gitValue.text = "No GitHub Link Yet"
                                }
                                if (dataSnapshot.child(query).child("College Email")
                                        .exists()
                                ) {
                                    emailValue.text = "Contact: $collegeEmailSnap"
                                } else {
                                    emailValue.text = "Contact: $email"
                                }

                                addFriend.setOnClickListener {
                                    myRefEmail.child(query).child("Pending Friend Request").child(uid.toString())
                                        .child("UID").setValue(uid.toString())
                                    Toast.makeText(
                                        applicationContext,
                                        "Friend Request Sent!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                messageButton.setOnClickListener {
                                    chatIntent.putExtra(ID, query)
                                    chatIntent.putExtra(USER_KEY, name)
                                    startActivity(chatIntent)
                                }
                            } else {
                                layout.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "No User Found",
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
        val profileButton = findViewById<View>(R.id.profile)
        profileButton.setOnClickListener {
            startActivity(intentPROFILE)
        }
        val smsButton = findViewById<View>(R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
    }
    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child("Pending Friend Request").exists()) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerViewNewMessage = findViewById<View>(R.id.recyclerview2) as RecyclerView
                snapshot.child("Pending Friend Request").children.forEach {
                    val user = it.key
                    if (user != null && user != FirebaseAuth.getInstance().currentUser?.uid) {
                        adapter.add(Friends(user))
                        recyclerViewNewMessage.adapter = adapter
                    }
                }
                }
            }
            override fun onCancelled(snapshot: DatabaseError) {}
        })


    }
    private fun fetchName(key: String?) {
        val ref = FirebaseDatabase.getInstance().getReference("users").child(key.toString()).child("Name")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.value
            }
            override fun onCancelled(error: DatabaseError) {}
        })
            }
}

