package finals.project.smsPage


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase.*
import finals.project.R
import finals.project.data.HomeActivity
import finals.project.data.PostActivity
import okhttp3.internal.userAgent
import java.text.DateFormat


class MainActivity : AppCompatActivity() {

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
    val listOfMessages: ListView = findViewById<ListView>(R.id.list_of_messages)
        val adapter = object : FirebaseListAdapter<ChatMessage?>(this, ChatMessage.class, R.layout.message)

    fun populateView(v: View, model: ChatMessage, position: Int) {

        val messageText: TextView = v.findViewById<View>(R.id.message_text) as TextView
        val messageUser: TextView = v.findViewById<View>(R.id.message_user) as TextView
        val messageTime: TextView = v.findViewById<View>(R.id.message_time) as TextView

        messageText.text = model.messageText
        messageUser.text = model.messageUser

        /* messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
             model.messageTime
         ))*/
    }
    //listOfMessages.adapter = adapter
}

}




