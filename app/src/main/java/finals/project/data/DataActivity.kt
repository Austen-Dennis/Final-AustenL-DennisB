package finals.project.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import java.lang.reflect.Array.set

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun dataBase(uid: String?, name: String?, email: String?) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {
                myRef.child("Name").setValue(name)
                myRef.child("Email").setValue(email)
            }
        }

        fun nameChange(uid: String?, name: String?) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {
                myRef.child("Name").setValue(name)
            }
        }

        fun profilePic(uid: String?) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {

            }
        }
    }
}
