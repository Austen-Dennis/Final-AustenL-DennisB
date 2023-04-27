package finals.project.activities

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R
import finals.project.smsPage.userinfo.User

private val View.username_text: TextView
    get() {
        return findViewById<View>(R.id.username_text) as TextView
    }

class Friends(private val key: User?) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_text.text = key?.Name
    }

    override fun getLayout(): Int {
        return R.layout.friend_row
    }
}