package finals.project.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import finals.project.R


class FriendProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
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

