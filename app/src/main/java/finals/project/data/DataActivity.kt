package finals.project.data

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import android.os.Bundle


@SuppressLint("SetTextI18n")
class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
    }

    //companion object contains all functions for passing data into the realtime database via firebase
    companion object {
        fun dataBase(uid: String?, name: String?, email: String?) {
            val myRef = FirebaseDatabase.getInstance().getReference("users").child(uid.toString())
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.child("Name").exists()) {
                            myRef.child("Email").setValue(email)
                            myRef.child("uid").setValue(uid)
                        } else {
                            myRef.child("Email").setValue(email)
                            myRef.child("Name").setValue(name)
                            myRef.child("uid").setValue(uid)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
        }

                    //verifies that companion object can be reached
                    fun isReachable(): Boolean {
                        return true
                    }

                    fun nameChange(uid: String?, name: String?) {
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users/$uid")
                        if (uid != null) {
                            myRef.child("Name").setValue(name)
                        }
                    }

                    fun gitHubLink(uid: String?, newLink: String) {
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users/$uid")
                        if (uid != null) {
                            myRef.child("GitHub Link").setValue(newLink)
                        }
                    }

                    fun bioAdd(uid: String?, newBio: String) {
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users/$uid")
                        if (uid != null) {
                            myRef.child("Bio").setValue(newBio)
                        }
                    }

                    fun collegeEmail(uid: String?, collegeEmail: String) {
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users/$uid")
                        if (uid != null) {
                            myRef.child("College Email").setValue(collegeEmail)
                        }
                    }
                }

            }



