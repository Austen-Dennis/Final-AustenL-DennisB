package finals.project.data

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R

private val View.username_text: TextView
    get() {
        return findViewById<View>(R.id.username_text) as TextView
    }

class Friends(private val key: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_text.text = key
    }


    override fun getLayout(): Int {
        return R.layout.user_row
    }
}