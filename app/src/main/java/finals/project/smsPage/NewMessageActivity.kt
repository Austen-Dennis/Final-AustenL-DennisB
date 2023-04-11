package finals.project.smsPage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.auth.User
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
        lateinit var listView: ListView
        lateinit var list: ArrayList<String>
        lateinit var adapter: ArrayAdapter<*>
        list = ArrayList()
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        fetchUsers(list, adapter)
    }

    private fun fetchUsers(list: ArrayList<String>, adapter: ArrayAdapter<String>) {

        val ref = FirebaseDatabase.getInstance().getReference("users/")
        System.out.println(ref)
        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            @SuppressLint("RestrictedApi")
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {

                    Log.d("NewMessage", snapshot.toString())

                    val user = snapshot.getValue(DataActivity.User::class.java)
                    System.out.println(user)
                    if (user!=null) {
                        val listView = findViewById(R.id.recyclerview) as ListView
                        list.add(user.toString())
                        listView.adapter = adapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

class UserItem(val user: String) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
       // val user = DataActivity.User(user.toString())
        viewHolder.itemView.username_text.text = user
    }


    override fun getLayout(): Int {
        return R.layout.user_row
    }
}



