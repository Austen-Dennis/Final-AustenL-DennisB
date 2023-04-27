package finals.project.smsPage.chatitems

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R
import finals.project.smsPage.userinfo.User

private val View.text_to_row: TextView
    get() {
        return findViewById<View>(R.id.text_to_row) as TextView
    }

//sets view of messages from other users
class ChatToItem(val text: String, val user: User): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text_to_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }


}