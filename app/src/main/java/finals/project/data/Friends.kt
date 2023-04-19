package finals.project.smsPage

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R

private val View.username_text: TextView
    get() {
        val username_text = findViewById<View>(R.id.username_text) as TextView
        return username_text
    }

class Friends(val key: String) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_text.text = key
    }


    override fun getLayout(): Int {
        return R.layout.user_row
    }
}