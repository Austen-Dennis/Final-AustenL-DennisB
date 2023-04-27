package finals.project.activities

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.widget.Toolbar
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
import finals.project.R
import finals.project.smsPage.userinfo.User

@Suppress("MissingInflatedId", "SuspiciousIndentation", "SetTextI18n")
class HomeActivity : AppCompatActivity() {
    companion object {
        fun isReachable(): Any {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // sets the view using the activity_friend_search
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)

        //data display
        val profileTitle = findViewById<View>(R.id.profileTitle) as TextView
        val searchView = findViewById<View>(R.id.searchView) as SearchView
        val emailValue = findViewById<View>(R.id.emailValue) as TextView
        val nameValue = findViewById<View>(R.id.nameValue) as TextView
        val gitValue = findViewById<View>(R.id.gitValue) as TextView
        val bioValue = findViewById<View>(R.id.bioValue) as TextView
        val friendMessage = findViewById<View>(R.id.NoFriends) as TextView

        //navigation and views
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val addFriend = findViewById<View>(R.id.addFriend)
        val removeFriend = findViewById<View>(R.id.removeFriend)
        val layout = findViewById<View>(R.id.layout1)
        val layout2 = findViewById<View>(R.id.layout2)

        //Firebase Information
        val uid = FirebaseAuth.getInstance().uid
        val mAuth = FirebaseAuth.getInstance()
        val currentUserId = mAuth.currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("users/")

        //View Initialization
        removeFriend.visibility = View.GONE
        friendMessage.visibility = View.GONE
        layout.visibility = View.VISIBLE
        layout2.visibility = View.GONE

        fetchUsers()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                userRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.child(query).exists() && query != currentUserId) {
                                val homeButton = findViewById<View>(R.id.home)
                                homeButton.setOnClickListener {
                                    startActivity(intentHOME)
                                }
                                layout2.visibility = View.VISIBLE
                                layout.visibility = View.GONE

                                profileTitle.text = "Profile Search"

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
                                nameValue.text = name

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
                                                userRef.child(query).child("Friends").child(uid.toString()).child("UID").setValue(uid.toString())
                                                userRef.child(uid.toString()).child("Friends").child(query).child("UID").setValue(query)
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Friend Request Sent!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        removeFriend.setOnClickListener {
                                            userRef.child(query).child("Friends").child(uid.toString()).removeValue()
                                            userRef.child(uid.toString()).child("Friends").child(query).removeValue()
                                            Toast.makeText(
                                                applicationContext,
                                                "Friend Removed!",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            removeFriend.visibility = View.GONE
                                            addFriend.visibility = View.VISIBLE
                                        }
                                            }
                                    override fun onCancelled(error: DatabaseError) {
                                    }
                                })
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
                snapshot.children.forEach { _ ->
                    if (snapshot.child("Friends").exists()) {
                        val adapter = GroupAdapter<GroupieViewHolder>()
                        val recyclerViewFriends =
                            findViewById<View>(R.id.recyclerview) as RecyclerView
                        snapshot.child("Friends").children.forEach {
                            val userName = it.key
                            val ref = FirebaseDatabase.getInstance().getReference("users")
                            ref.addListenerForSingleValueEvent(object: ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    snapshot.children.forEach { friendRef ->
                                        val user = friendRef.getValue(User::class.java)
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