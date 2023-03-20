package finals.project.smsPage


import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase.*
import finals.project.R
import finals.project.data.HomeActivity
import finals.project.data.PostActivity
import okhttp3.internal.userAgent


class MainActivity : AppCompatActivity() {
    private var adapter: FirebaseListAdapter<ChatMessage>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

            displayChatMessages()
        val intentPOST = Intent(this, PostActivity::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val postButton = findViewById<View>(R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPOST)
        }
        val homeButton = findViewById<View>(R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val input = findViewById<View>(R.id.input) as EditText
            // Read the input field and push a new instance
            // of ChatMessage to the Firebase database
            getInstance()
                .reference
                .push()
                .setValue(
                    ChatMessage(
                        input.text.toString(),
                        FirebaseAuth.getInstance()
                            .currentUser
                            ?.displayName ?: userAgent
                    )
                )
            // Clear the input
            input.setText("")
        }


    }

private fun displayChatMessages() {
    val leaderID = ""
    val options = FirebaseListOptions.Builder<ChatMessage>()
        .setQuery(
            getInstance().getReference("Lobbies").child(leaderID).child("Messages"),
            ChatMessage::class.java
        ).setLayout(finals.project.R.layout.messages).build()

    adapter = object : FirebaseListAdapter<ChatMessage>(options) {
        override fun populateView(v: View, model: ChatMessage, position: Int) {
            // Get references to the views of message.xml
            val messageText = v.findViewById<TextView>(finals.project.R.id.message_text)
            val messageUser = v.findViewById<TextView>(finals.project.R.id.message_user)
            val messageTime = v.findViewById<TextView>(finals.project.R.id.message_time)

            // Set their text
            messageText.text = model.messageText
            messageUser.text = model.messageUser

            // Format the date before showing it
            messageTime.setText(
                DateFormat.format(
                    "dd-MM-yyyy (HH:mm:ss)",
                    model.messageTime
                )
            )
        }
    }
}

}




