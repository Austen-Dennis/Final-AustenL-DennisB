package finals.project.data

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.datatransport.runtime.TransportRuntime.initialize
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.reflect.Array.set


class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        fun initialize() {

        }
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
        fun isReachable(): Boolean {
            val reachable = true
            return reachable
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

        fun bioAdd(uid: String?, newBio: String) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {
                myRef.child("Bio").setValue(newBio)
            }
        }

        fun collegeEmail(uid: String?, collegeEmail: String) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/" + uid)
            if (uid != null) {
                myRef.child("College Email").setValue(collegeEmail)
            }
        }
        fun userGrab(uid: String?): String {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users/").child(uid.toString())
            return ""
        }


    }

}


