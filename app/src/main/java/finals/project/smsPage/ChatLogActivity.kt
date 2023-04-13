package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R
import finals.project.data.FriendSearchActivity


class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        supportActionBar?.title = "Chat Log"

        val username = intent.getStringExtra(NewMessageActivity.USER_KEY)

        supportActionBar?.title = username

        val SendButton = findViewById<View>(R.id.sendButton)
        val message = findViewById<View>(R.id.Enter_message) as EditText
        val name = intent.getStringExtra(FriendSearchActivity.USER_KEY)
        val uid = intent.getStringExtra(FriendSearchActivity.ID)
        val ref = FirebaseDatabase.getInstance().getReference("users/").child(uid.toString())
        val chat_log = findViewById<View>(R.id.chat_log) as RecyclerView
        val adapter = GroupAdapter<GroupieViewHolder>()
        supportActionBar?.title = name
        SendButton.setOnClickListener {
            val message = message.getText().toString()
            ref.child("Messages").push().setValue(message)
            adapter.add(ChatFromItem())
            adapter.add(ChatToItem())
             chat_log.adapter= adapter
        }

    }
}
class ChatFromItem: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }

}
class ChatToItem: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }

}