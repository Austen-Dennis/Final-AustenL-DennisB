package finals.project.data

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import finals.project.R
import io.reactivex.rxjava3.internal.util.NotificationLite.getValue


class FriendProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)
    }
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
        fun friendSearch(uid: String) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference(uid)
            /*myRef.get().
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                    Log.d(TAG, "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })*/
        }

    }

}

