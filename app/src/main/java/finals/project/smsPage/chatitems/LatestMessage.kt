package finals.project.smsPage.chatitems

import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R
import finals.project.smsPage.userinfo.User

private val View.latestMessageRow: TextView
    get() {
        return findViewById<View>(R.id.latestMessage) as TextView
    }
private val View.usernameMessage: TextView
    get() {
        return findViewById<View>(R.id.usernameLatestMessage) as TextView
    }

class LatestMessage(private val chatMessage: ChatMessage) : Item<GroupieViewHolder>() {
    var chatPartnerUser: User? = null
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.latestMessageRow.text = chatMessage.text
        val chatPartner: String = if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
            chatMessage.toID
        } else {
            chatMessage.fromId
        }
        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartner")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartnerUser = snapshot.getValue(User::class.java)
                viewHolder.itemView.usernameMessage.text = chatPartnerUser?.Name
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }


    override fun getLayout(): Int {
        return R.layout.latest_messages
    }

}