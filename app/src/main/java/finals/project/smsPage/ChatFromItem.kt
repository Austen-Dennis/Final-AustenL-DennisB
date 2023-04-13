package finals.project.smsPage

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import finals.project.R

private val View.text_from_row: TextView
    get() {
        val text_from_row = findViewById<View>(R.id.text_from_row) as TextView
        return text_from_row
    }

class ChatFromItem(val text: String, val user: User): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text_from_row.text = text
        //TODO
        //val uri=user.profileImageUrl
        //val targetImage = viewHolder.itemView.from_image
        //Picasso.get().load(uri).into(targetImage)
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}