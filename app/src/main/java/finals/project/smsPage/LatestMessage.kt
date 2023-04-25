package finals.project.smsPage

import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R
private val View.latestMessageRow: TextView
    get() {
        return findViewById<View>(R.id.latestMessage) as TextView
    }
class LatestMessage(val chatmessage: ChatMessage): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.itemView.latestMessageRow.text = chatmessage.text
    }

    override fun getLayout(): Int {
        return R.layout.latest_messages
    }

}