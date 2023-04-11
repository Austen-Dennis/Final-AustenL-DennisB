package finals.project.smsPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import finals.project.R
import finals.project.data.DataActivity
import finals.project.data.model.User
import okhttp3.internal.userAgent


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
        fetchUsers()
    }

    private fun fetchUsers() {

        //val ref = FirebaseDatabase.getInstance().getReference("users/").orderByChild("Name")
        val ref = FirebaseDatabase.getInstance().getReference("users")
        //System.out.println(ref)
        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            @SuppressLint("RestrictedApi")
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = snapshot.value
                    System.out.println(nameGet(value.toString()))
                    //System.out.println(name)
                //System.out.println(snapshot)
                //System.out.println(ref)
                val adapter = GroupAdapter<GroupieViewHolder>()
                val UserList = ArrayList<User>()
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    if (user != null) {
                        //UserList.add(user)
                        adapter.add(UserItem(user.toString()))
                    }
                }

                    }

                }
            fun nameGet(value: String): String? {
                val name = value?.substring(0, value.indexOf("Name="))

                name?.trim()
                return name


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



