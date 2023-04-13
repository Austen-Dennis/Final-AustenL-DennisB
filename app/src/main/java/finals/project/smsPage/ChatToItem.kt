package finals.project.smsPage

import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R

private val View.text_to_row: TextView
    get() {
        val text_to_row = findViewById<View>(R.id.text_to_row) as TextView
        return text_to_row
    }

class ChatToItem(val text: String): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text_to_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }


}