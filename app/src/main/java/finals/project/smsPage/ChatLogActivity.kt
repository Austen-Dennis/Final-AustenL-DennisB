package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import finals.project.R
import finals.project.data.FriendSearchActivity


class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        val SendButton = findViewById<View>(R.id.sendButton)
        val message = findViewById<View>(R.id.Enter_message) as EditText
        val name = intent.getStringExtra(FriendSearchActivity.USER_KEY)
        val uid = intent.getStringExtra(FriendSearchActivity.ID)
        val ref = FirebaseDatabase.getInstance().getReference("users/").child(uid.toString())
        supportActionBar?.title = name
        System.out.println(name + " " + uid)
        SendButton.setOnClickListener {
            val message = message.getText().toString()
            ref.child("Messages").push().setValue(message)
        }

    }
}