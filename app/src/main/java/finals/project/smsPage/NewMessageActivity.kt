package finals.project.smsPage

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import androidx.appcompat.app.AppCompatActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.GroupAdapter
import android.content.Intent
import android.view.View
import android.os.Bundle
import android.util.Log
import finals.project.R

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"
        fetchUsers()
    }
    companion object{
        val USER_KEY = "USER_KEY"
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerview_newmessage = findViewById<View>(R.id.recyclerview) as RecyclerView
                snapshot.children.forEach {
                    Log.d("NewMessage", it.toString())
                        val user = it.getValue(User::class.java)
                        if (user != null) {
                            adapter.add(UserItem(user))
                        }
                    }
                    adapter.setOnItemClickListener{item, view ->
                    val userItem = item as UserItem
                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)
                    finish()
                }
                recyclerview_newmessage.adapter = adapter
                }

            override fun onCancelled(snapshot: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}



