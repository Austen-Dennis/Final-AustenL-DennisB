package finals.project.data

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.data.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import finals.project.R

class FriendProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)
        val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("message")
        val myRef = database.getReference("message")
        myRef.push().setValue("Hello, World!")
    }
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
        fun friendSearch(uid: String) {
            //FirebaseAuth.getInstance().zzl(uid)
        }

    }

}

