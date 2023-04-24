package finals.project.smsPage

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R

class LatestMessage: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.latest_messages
    }

}