package finals.project.smsPage

import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R

private val View.username_text: TextView
    get() {
        val username_text = findViewById<View>(R.id.username_text) as TextView
        return username_text
    }

class UserItem(val user: User) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_text.text = user.Name
    }


    override fun getLayout(): Int {
        return R.layout.user_row
    }
}