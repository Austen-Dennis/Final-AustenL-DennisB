package finals.project.smsPage


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.Query
import finals.project.R
import finals.project.data.HomeActivity
import finals.project.data.PostActivity
import okhttp3.internal.userAgent
class SmsPage : AppCompatActivity() {
    private var adapter: FirebaseListAdapter<ChatMessage>? = null
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.smspage)

        displayChatMessages()
        val intentHOME = Intent(this, HomeActivity::class.java)
        val intentPOST = Intent(this, PostActivity::class.java)
        val homeButton = findViewById<View>(R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }
        val postButton = findViewById<View>(R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPOST)
        }
        val smsButton = findViewById<View>(R.id.sms)

        val typeBox = findViewById<View>(R.id.input)
        typeBox.setOnClickListener {
            homeButton.visibility = View.INVISIBLE
            postButton.visibility = View.INVISIBLE
            smsButton.visibility = View.INVISIBLE
        }

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val input = findViewById<View>(R.id.input) as EditText
            // Read the input field and push a new instance
            // of ChatMessage to the Firebase database
            getInstance().reference.push().setValue(ChatMessage(input.text.toString(),
                FirebaseAuth.getInstance().currentUser?.displayName ?: userAgent))
            // Clear the input
            input.setText("")
        }
    }
    private fun displayChatMessages() {
        val listOfMessage = findViewById<View>(R.id.list_of_messages) as ListView
        val query: Query = getInstance().reference.child("chats")
        val options = FirebaseListOptions.Builder<ChatMessage>()
            .setQuery(query, ChatMessage::class.java)
            .setLayout(android.R.layout.simple_list_item_1)
            .build()
        adapter = object : FirebaseListAdapter<ChatMessage>(options) {
            override fun populateView(
                @NonNull v: View,
                @NonNull model: ChatMessage,
                position: Int
            ) {
                val messageText: TextView
                val messageUser: TextView
                val messageTime: TextView
                messageText = v.findViewById<TextView>(R.id.message_text)
                messageUser = v.findViewById<TextView>(R.id.message_user)
                messageTime = v.findViewById<TextView>(R.id.message_time)
                messageText.text = model.messageText
                messageUser.text = model.messageUser
                messageTime.setText(DateFormat.format("dd-MM-yyyy(HH:mm:ss)", model.messageTime))
            }
        }
        listOfMessage.adapter = adapter
    }
}






