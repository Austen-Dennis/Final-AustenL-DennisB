package finals.project.smsPage

import android.content.Intent
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.GroupAdapter
import android.widget.EditText
import android.view.View
import android.os.Bundle
import android.util.Log
import finals.project.R

@Suppress("DEPRECATION")
class ChatLogActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ChatLog"
        fun isReachable(): Any {
            return true
        }
    }

    val adapter = GroupAdapter<GroupieViewHolder>()
    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        val backButton = findViewById<View>(R.id.back)
        val intentBack = Intent(this, LatestMessagesActivity::class.java)
        toUser = intent.getParcelableExtra(NewMessageActivity.USER_KEY)

        val chatLog = findViewById<View>(R.id.chat_log) as RecyclerView
        supportActionBar?.title = toUser?.Name

        listenForMessages()
        val sendButton = findViewById<View>(R.id.sendButton)

        chatLog.adapter = adapter

        sendButton.setOnClickListener {
            Log.d(TAG,"Attempt to send message")
            sendMessage()
        }
        backButton.setOnClickListener {
            startActivity(intentBack)
        }
    }

    //grabs database info and passes it back into database with message attached
    private fun sendMessage() {
        val message = findViewById<View>(R.id.Enter_message) as EditText
        val text = message.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user!!.uid

        if (fromId == null) return
        val chatLog = findViewById<View>(R.id.chat_log) as RecyclerView
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRef = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
        val chatMessage = ChatMessage(ref.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG,"Message saved")
                message.text.clear()
                chatLog.scrollToPosition(adapter.itemCount - 1)
            }
        toRef.setValue(chatMessage)

        val latestMessageFromRef = FirebaseDatabase.getInstance().getReference("/latest-message/$fromId/$toId")
        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-message/$toId/$fromId")
        latestMessageFromRef.setValue(chatMessage)
        latestMessageToRef.setValue(chatMessage)
    }

    //updates view everytime a message is added to database
    private fun listenForMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, snapshot2: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        adapter.add(ChatToItem(chatMessage.text, toUser!!))
                    } else {
                        val currentUser = LatestMessagesActivity.currentUser ?: return
                        adapter.add(ChatFromItem(chatMessage.text, currentUser))
                    }
                }
            }
            // These are required for this function to function, but have no use in our program.
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }
        })
    }
}


