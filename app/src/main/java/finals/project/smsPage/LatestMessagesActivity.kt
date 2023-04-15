package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import finals.project.ui.login.LoginActivity
import android.content.Intent
import android.view.MenuItem
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import finals.project.R

class LatestMessagesActivity : AppCompatActivity() {
companion object{
    var currentUser: User? = null
    fun isReachable(): Any {
        val reachable = true;
        return reachable
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        val intentMessage = Intent(this, NewMessageActivity::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)
        fetchUser()
        
        val newMessageButton = findViewById<View>(R.id.new_message)
        newMessageButton.setOnClickListener {
            startActivity(intentMessage)

        }
    }

    //grabs and displays all existing users
    private fun fetchUser(){
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(User::class.java)
                Log.d("LatestMessages","Current user ${currentUser?.Name}")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
