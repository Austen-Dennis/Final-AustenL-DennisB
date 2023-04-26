package finals.project.smsPage

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R

private val View.text_from_row: TextView
    get() {
        return findViewById<View>(R.id.text_from_row) as TextView
    }

//sets view of messages from other users
class ChatFromItem(val text: String, val user: User) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text_from_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}