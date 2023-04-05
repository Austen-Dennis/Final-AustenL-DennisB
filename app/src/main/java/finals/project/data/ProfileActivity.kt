package finals.project.data

import android.annotation.SuppressLint
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import finals.project.smsPage.SmsPage
import finals.project.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_profile)
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        val intentLogin = Intent(this, LoginActivity::class.java)
        val intentPost = Intent(this, PostActivity::class.java)
        val intentSms = Intent(this, SmsPage::class.java)
        val intentSearch = Intent(this, FriendSearchActivity::class.java)
        val intentReturn = Intent(this, HomeActivity::class.java)
        val urlRelease = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR/releases"
        val releaseIntent = Intent(Intent.ACTION_VIEW)
        val urlProjectInfo = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR#get-together"
        val projectInfoIntent = Intent(Intent.ACTION_VIEW)
        val profilePicture = findViewById<View>(finals.project.R.id.profilePic)
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val hideInfoButton = findViewById<View>(finals.project.R.id.hideInfo)
        val showInfoButton = findViewById<View>(finals.project.R.id.displayInfo)
        val copyIDButton = findViewById<View>(finals.project.R.id.copyID)
        val changeName = findViewById<View>(finals.project.R.id.changeName)
        val nameText = findViewById<View>(finals.project.R.id.newName) as EditText
        val submit = findViewById<View>(finals.project.R.id.submit)
        val uid = FirebaseAuth.getInstance().uid
        val emailText = findViewById<View>(finals.project.R.id.email) as TextView
        val userID = findViewById<View>(finals.project.R.id.user) as TextView
        val myRef = FirebaseDatabase.getInstance().getReference("users")

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val email = dataSnapshot.child(uid.toString()).child("Email").getValue().toString()
                    val name = dataSnapshot.child(uid.toString()).child("Name").getValue().toString()
                    val clip: ClipData = ClipData.newPlainText("simple text", uid)
                    profileTitle.text = "Welcome " + name + "!"
                    emailText.text = "Email: \n" + email
                    userID.text = "User ID: \n" + uid
                    profilePicture.visibility = View.GONE
                    emailText.visibility= View.GONE
                    userID.visibility=View.GONE
                    hideInfoButton.visibility=View.GONE
                    copyIDButton.visibility=View.GONE
                    changeName.visibility=View.GONE
                    nameText.visibility=View.GONE
                    submit.visibility=View.GONE



                    showInfoButton.setOnClickListener {
                        emailText.visibility= View.VISIBLE
                        userID.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.VISIBLE
                        showInfoButton.visibility=View.GONE
                        profilePicture.visibility = View.VISIBLE
                        copyIDButton.visibility=View.VISIBLE
                        changeName.visibility=View.VISIBLE
                    }
                    hideInfoButton.setOnClickListener {
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                        profilePicture.visibility = View.GONE
                        changeName.visibility=View.GONE
                        nameText.visibility=View.GONE
                        submit.visibility=View.GONE
                    }
                    changeName.setOnClickListener {
                        nameText.visibility = View.VISIBLE
                        submit.visibility=View.VISIBLE

                    }
                    submit.setOnClickListener {
                        val newName = nameText.getText().toString()
                        DataActivity.nameChange(uid, newName)
                        Toast.makeText(
                            applicationContext,
                            "Name Changed Successfully!",
                            Toast.LENGTH_LONG
                        ).show()
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                        profilePicture.visibility = View.GONE
                        changeName.visibility=View.GONE
                        nameText.visibility=View.GONE
                        submit.visibility=View.GONE
                    }
                    copyIDButton.setOnClickListener {
                        val clip: ClipData = ClipData.newPlainText("simple text", uid)
                        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(
                            applicationContext,
                            "Successfully Copied User ID",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    val updatePassButton = findViewById<View>(finals.project.R.id.update)
                    updatePassButton.setOnClickListener {
                        if (name != null) {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(name)
                            Toast.makeText(
                                applicationContext,
                                "Email sent to " + name,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    val releaseButton = findViewById<View>(finals.project.R.id.release)
                    releaseButton.setOnClickListener {
                        releaseIntent.data = Uri.parse(urlRelease)
                        startActivity(releaseIntent)
                    }
                    val projectInfoButton = findViewById<View>(finals.project.R.id.project)
                    projectInfoButton.setOnClickListener {
                        projectInfoIntent.data = Uri.parse(urlProjectInfo)
                        startActivity(projectInfoIntent)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })


        val postButton = findViewById<View>(finals.project.R.id.post)
        postButton.setOnClickListener {
            startActivity(intentPost)
        }

        val smsButton = findViewById<View>(finals.project.R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSms)
        }

        val returnButton = findViewById<View>(finals.project.R.id.home)
        returnButton.setOnClickListener {
            startActivity(intentReturn)
        }

        val soButton = findViewById<View>(finals.project.R.id.so)
        soButton.setOnClickListener {
            startActivity(intentLogin)
            FirebaseAuth.getInstance().signOut()
        }
        val searchButton = findViewById<View>(finals.project.R.id.search)
        searchButton.setOnClickListener {
            startActivity(intentSearch)
        }
    }
}