package finals.project.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import finals.project.R
import finals.project.smsPage.chatitems.ChatLogActivity
import finals.project.smsPage.chatitems.ChatMessage
import finals.project.smsPage.chatitems.LatestMessage
import finals.project.smsPage.userinfo.User


class LatestMessagesActivity : AppCompatActivity() {
    companion object {
        var currentUser: User? = null
        const val TAG = "latest Message"
        val adapter = GroupAdapter<GroupieViewHolder>()
        fun isReachable(): Any {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val intentMessage = Intent(this, NewMessageActivity::class.java)
        val intentHome = Intent(this, HomeActivity::class.java)
        val intentProfile = Intent(this, ProfileActivity::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)
        listenForNewestMessage()
        fetchUser()

        val newMessageButton = findViewById<View>(R.id.new_message)
        newMessageButton.setOnClickListener {
            startActivity(intentMessage)
        }
        val homeButton = findViewById<View>(R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHome)
        }
        val profileButton = findViewById<View>(R.id.profile)
        profileButton.setOnClickListener {
            startActivity(intentProfile)
        }
    }

    val messagesMap = HashMap<String, ChatMessage>()
    private fun refreshRecycler() {
        adapter.clear()
        messagesMap.values.forEach {
            adapter.add(LatestMessage(it))
        }
        val latestMessage = findViewById<RecyclerView>(R.id.newest_Messages)
        latestMessage.adapter = adapter
        latestMessage.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener { item, _ ->
            Log.d(TAG, "124")
            val intent = Intent(this, ChatLogActivity::class.java)
            val row = item as LatestMessage
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }
    }

    private fun listenForNewestMessage() {
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-message/$fromId")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java) ?: return
                messagesMap[snapshot.key!!] = chatMessage
                refreshRecycler()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java) ?: return
                messagesMap[snapshot.key!!] = chatMessage
                refreshRecycler()
            }
            // These are required for this function to function, but have no use in our program.
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    //grabs and displays all existing users
    private fun fetchUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(User::class.java)
                Log.d("LatestMessages", "Current user ${currentUser?.Name}")
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}
