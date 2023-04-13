package finals.project.smsPage

import com.xwray.groupie.GroupieViewHolder
import android.widget.TextView
import com.xwray.groupie.Item
import android.view.View
import com.squareup.picasso.Picasso
import finals.project.R

private val View.text_to_row: TextView
    get() {
        val text_to_row = findViewById<View>(R.id.text_to_row) as TextView
        return text_to_row
    }

class ChatToItem(val text: String, val user: User): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text_to_row.text = text
        //TODO
        //val uri=user.profileImageUrl
        //val targetImage = viewHolder.itemView.to_image
        //Picasso.get().load(uri).into(targetImage)
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }


}