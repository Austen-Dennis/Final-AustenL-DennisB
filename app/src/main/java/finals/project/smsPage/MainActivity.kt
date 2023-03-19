package finals.project.smsPage


import android.R
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import okhttp3.internal.userAgent
import java.text.DateFormat


class MainActivity : AppCompatActivity() {
    lateinit var adapter: FirebaseListAdapter<ChatMessage?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(finals.project.R.layout.activity_main)

            displayChatMessages();
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val input = findViewById<View>(R.id.input) as EditText
            // Read the input field and push a new instance
            // of ChatMessage to the Firebase database
            FirebaseDatabase.getInstance()
                .reference
                .push()
                .setValue(
                    ChatMessage(
                        input.text.toString(),
                        FirebaseAuth.getInstance()
                            .currentUser
                            ?.getDisplayName() ?: userAgent
                    )
                )
            // Clear the input
            input.setText("")
        }
    }

    }

    private fun displayChatMessages() {
        val listOfMessages: ListView = findViewById(R.id.list_of_messages) as ListView
        adapter = object : FirebaseListAdapter<ChatMessage?>(
            this, ChatMessage::class.java,
            R.layout.message, FirebaseDatabase.getInstance().reference
        ) {
            protected fun populateView(v: View, model: ChatMessage, position: Int) {
                // Get references to the views of message.xml
                val messageText: TextView = v.findViewById<View>(R.id.message_text) as TextView
                val messageUser: TextView = v.findViewById<View>(R.id.message_user) as TextView
                val messageTime: TextView = v.findViewById<View>(R.id.message_time) as TextView
                // Set their text
                messageText.setText(model.messageText)
                messageUser.setText(model.messageUser)
                // Format the date before showing it
                messageTime.setText(
                    DateFormat.format(
                        "dd-MM-yyyy (HH:mm:ss)",
                        model.messageTime
                    )
                )
            }
        }
        listOfMessages.adapter = adapter
    }
