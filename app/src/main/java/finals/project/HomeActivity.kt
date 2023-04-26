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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import finals.project.data.Friends
import finals.project.data.ProfileActivity
import finals.project.smsPage.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    companion object {
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

        //data display
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val searchView = findViewById<View>(finals.project.R.id.searchView) as SearchView
        val emailValue = findViewById<View>(finals.project.R.id.emailValue) as TextView
        val nameValue = findViewById<View>(finals.project.R.id.nameValue) as TextView
        val gitValue = findViewById<View>(finals.project.R.id.gitValue) as TextView
        val bioValue = findViewById<View>(finals.project.R.id.bioValue) as TextView
        val friendMessage = findViewById<View>(R.id.NoFriends) as TextView

        //navigation
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val chatIntent = Intent(this, ChatLogActivity::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)

        val addFriend = findViewById<View>(finals.project.R.id.addFriend)
        val layout2 = findViewById<View>(finals.project.R.id.layout2)
        val removeButton = findViewById<View>(R.id.removeFriend)
        val layout = findViewById<View>(R.id.layout1)
        val uid = FirebaseAuth.getInstance().uid
        val mAuth = FirebaseAuth.getInstance()
        val currentUserId = mAuth.currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val myRefEmail = database.getReference("users/")

        removeButton.visibility = View.GONE
        friendMessage.visibility = View.GONE
        layout.visibility = View.VISIBLE
        layout2.visibility = View.GONE
        fetchUsers()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                myRefEmail.addValueEventListener(object : ValueEventListener {
                    @SuppressLint("SetTextI18n")
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.child(query).exists() && query != currentUserId) {
                                val homeButton = findViewById<View>(R.id.home)
                                homeButton.setOnClickListener {
                                    startActivity(intentHOME)
                                }
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
                                val ref = FirebaseDatabase.getInstance().getReference("users").child(uid.toString()).child("Friends")
                                ref.addListenerForSingleValueEvent(object: ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        snapshot.children.forEach {
                                            if (query == it.child("UID").value) {
                                                addFriend.visibility = View.GONE
                                                val removeButton = findViewById<View>(R.id.removeFriend)
                                                removeButton.visibility = View.VISIBLE
                                        }
                                        }
                                            addFriend.setOnClickListener {
                                                myRefEmail.child(query).child("Friends").child(uid.toString()).child("UID").setValue(uid.toString())
                                                myRefEmail.child(uid.toString()).child("Friends").child(query).child("UID").setValue(query)
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Friend Request Sent!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        removeButton.setOnClickListener {
                                            myRefEmail.child(query).child("Friends").child(uid.toString()).removeValue()
                                            myRefEmail.child(uid.toString()).child("Friends").child(query).removeValue()
                                            Toast.makeText(
                                                applicationContext,
                                                "Friend Removed!",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            removeButton.visibility = View.GONE
                                            addFriend.visibility = View.VISIBLE
                                        }
                                            }
                                    override fun onCancelled(error: DatabaseError) {
                                    }
                                })
                                messageButton.setOnClickListener {
                                    chatIntent.putExtra(NewMessageActivity.USER_KEY, query)
                                    startActivity(chatIntent)
                                    finish()
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
        val refFriend = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
        refFriend.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val friendMessage = findViewById<View>(R.id.NoFriends) as TextView
                snapshot.children.forEach {
                    if (snapshot.child("Friends").exists()) {
                        val adapter = GroupAdapter<GroupieViewHolder>()
                        val recyclerViewFriends =
                            findViewById<View>(R.id.recyclerview) as RecyclerView
                        snapshot.child("Friends").children.forEach {
                            val userName = it.key
                            val ref = FirebaseDatabase.getInstance().getReference("users")
                            ref.addListenerForSingleValueEvent(object: ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    snapshot.children.forEach {
                                        val user = it.getValue(User::class.java)
                                        if (user?.uid == userName) {
                                            friendMessage.visibility = View.GONE
                                            adapter.add(Friends(user))
                                        }
                                    }
                                    recyclerViewFriends.adapter = adapter
                                    recyclerViewFriends.addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
                                }
                                override fun onCancelled(snapshot: DatabaseError) {
                                }
                            })
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}