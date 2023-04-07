package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import finals.project.R

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)


       /* supportActionBar?.title = "Select User"
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(UserItem())
        R.id.recyclerview.adapter = adapter*/
    }
}

/*
class UserItem: Item<RecyclerView.ViewHolder>() {
    override fun bind(viewHolder: RecyclerView.ViewHolder, position: Int) {
        //list for each user later on.
    }

    override fun getLayout(): Int {
        return R.layout.user_row
    }
}
*/
