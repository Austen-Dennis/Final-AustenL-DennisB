package finals.project.smsPage

import android.annotation.SuppressLint
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
import com.google.firebase.auth.FirebaseAuth
import finals.project.R
import finals.project.HomeActivity

class NewMessageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"
        val backButton = findViewById<View>(R.id.back)
        fetchUsers()
        backButton.setOnClickListener {
            val intentBack = Intent(this, HomeActivity::class.java)
            startActivity(intentBack)
        }
    }
    companion object {
        const val USER_KEY = "USER_KEY"
        fun isReachable(): Any {
            return true
        }
    }

    //grabs users information and displays it
    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerviewMessage = findViewById<View>(R.id.recyclerview) as RecyclerView
                snapshot.children.forEach {
                        val user = it.getValue(User::class.java)
                        if (user != null && user.uid != FirebaseAuth.getInstance().currentUser?.uid) {
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
                recyclerviewMessage.adapter = adapter
                }
            override fun onCancelled(snapshot: DatabaseError) {
            }
        })
    }
}



