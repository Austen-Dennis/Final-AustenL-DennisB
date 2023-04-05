package finals.project.data

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.reflect.Array.set

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun dataBase(uid: String?, name: String?, email: String?) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null && myRef.child("Name")==null) {
                myRef.child("Name").setValue(name)
                myRef.child("Email").setValue(email)
            } else if (uid!=null) {
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

        fun gitHubLink(uid: String?, newLink: String) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {
                myRef.child("GitHub Link").setValue(newLink)
            }
        }

    }
}


