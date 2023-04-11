package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R
import finals.project.data.DataActivity



private val View.username_text: TextView
    get() {
        val username_textview = findViewById(R.id.username_text) as TextView
        return username_textview
    }


class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"
        val adapter = GroupAdapter<GroupieViewHolder>()
        val recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
        recyclerView.adapter=adapter


        fetchUsers()
    }
    private fun fetchUsers(){
        val ref = FirebaseDatabase.getInstance().getReference("users/")
        System.out.println(ref)
        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
                recyclerView.adapter=adapter
                snapshot.children.forEach {

                    Log.d("NewMessage", snapshot.toString())

                    val user = snapshot.getValue(DataActivity.User::class.java)
                    System.out.println(user)
                    if (user!=null) {
                        adapter.add(UserItem(user))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

class UserItem(val user: DataActivity.User?) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
       // val user = DataActivity.User(user.toString())
        //viewHolder.itemView.username_text.text = user.uid + "FUCK FUCK"
    }


    override fun getLayout(): Int {
        return R.layout.user_row
    }
}



