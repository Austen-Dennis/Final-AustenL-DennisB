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
        val TAG = "ChatLog"
        fun isReachable(): Any {
            val reachable = true;
            return reachable
        }
    }

    val adapter = GroupAdapter<GroupieViewHolder>()
    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        val backButton = findViewById<View>(R.id.back)
        val intentBack = Intent(this, LatestMessagesActivity::class.java)
        toUser = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        val chat_log = findViewById<View>(R.id.chat_log) as RecyclerView
        supportActionBar?.title = toUser?.Name

        listenForMessages()
        val SendButton = findViewById<View>(R.id.sendButton)

        chat_log.adapter = adapter

        SendButton.setOnClickListener {
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
        val chat_log = findViewById<View>(R.id.chat_log) as RecyclerView
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRef = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
        val chatMessage = ChatMessage(ref.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG,"Message saved")
                message.text.clear()
                chat_log.scrollToPosition(adapter.itemCount - 1)
            }
        toRef.setValue(chatMessage)
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

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

        })
    }
}


