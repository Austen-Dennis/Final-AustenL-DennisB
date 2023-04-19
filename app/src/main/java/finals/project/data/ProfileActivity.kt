package finals.project.data

import com.google.firebase.database.ValueEventListener
import finals.project.smsPage.LatestMessagesActivity
import com.google.firebase.database.FirebaseDatabase
import android.view.inputmethod.InputMethodManager
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import androidx.appcompat.app.AppCompatActivity
import finals.project.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.widget.Toolbar
import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.os.Bundle
import android.content.*
import android.view.View
import android.util.Log
import android.net.Uri

@Suppress("DEPRECATION")
class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.activity_profile)
        val urlProjectInfo = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR#get-together"
        val urlRelease = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR/releases"
        val profileTitle = findViewById<View>(finals.project.R.id.profileTitle) as TextView
        val collegeEmail = findViewById<View>(finals.project.R.id.collegeEmail) as EditText
        val emailValue = findViewById<View>(finals.project.R.id.emailValue) as TextView
        val intentSearch = Intent(this, HomeActivity::class.java)
        val nameValue = findViewById<View>(finals.project.R.id.nameValue) as TextView
        val intentSms = Intent(this, LatestMessagesActivity::class.java)
        val bioValue = findViewById<View>(finals.project.R.id.bioValue) as TextView
        val gitValue = findViewById<View>(finals.project.R.id.gitValue) as TextView
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        val nameText = findViewById<View>(finals.project.R.id.newName) as EditText
        val emailText = findViewById<View>(finals.project.R.id.email) as TextView
        val showInfoButton = findViewById<View>(finals.project.R.id.displayInfo)
        val gitHub = findViewById<View>(finals.project.R.id.GitHub) as EditText
        val profilePicture = findViewById<View>(finals.project.R.id.profilePic)
        val intentReturn = Intent(this, HomeActivity::class.java)
        val intentLogin = Intent(this, LoginActivity::class.java)
        val userID = findViewById<View>(finals.project.R.id.user) as TextView
        val hideInfoButton = findViewById<View>(finals.project.R.id.hideInfo)
        val myRef = FirebaseDatabase.getInstance().getReference("users")
        val friendButton = findViewById<View>(finals.project.R.id.addFriend)
        val emailSub = findViewById<View>(finals.project.R.id.submitEmail)
        val bio = findViewById<View>(finals.project.R.id.bio) as EditText
        val gitHubSub = findViewById<View>(finals.project.R.id.submitGit)
        val copyIDButton = findViewById<View>(finals.project.R.id.copyID)
        val submit = findViewById<View>(finals.project.R.id.submit)
        val bioSub = findViewById<View>(finals.project.R.id.subBio)
        val projectInfoIntent = Intent(Intent.ACTION_VIEW)
        val releaseIntent = Intent(Intent.ACTION_VIEW)
        val uid = FirebaseAuth.getInstance().uid
        setSupportActionBar(toolbar as Toolbar?)

        myRef.addValueEventListener(object: ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val collegeEmailSnap = dataSnapshot.child(uid.toString()).child("College Email").getValue().toString()
                    val gitLink = dataSnapshot.child(uid.toString()).child("GitHub Link").getValue().toString()
                    val bioSnap = dataSnapshot.child(uid.toString()).child("Bio").getValue().toString()
                    val email = dataSnapshot.child(uid.toString()).child("Email").getValue().toString()
                    val name = dataSnapshot.child(uid.toString()).child("Name").getValue().toString()
                    val clip: ClipData = ClipData.newPlainText("simple text", uid)

                    //TODO: add implementation for friend requests to be added to list
                    val pendingRequest = dataSnapshot.child(uid.toString()).child("Pending Friend Request").getValue().toString()
                    profileTitle.text = "Your Profile"

                    //checks if the data value can be found in database, and displays it
                    if (dataSnapshot.child(uid.toString()).child("Name").exists()) {
                        nameValue.text = name
                    } else {
                        nameValue.text = email
                    }
                    if (dataSnapshot.child(uid.toString()).child("Bio").exists()) {
                        bioValue.text = bioSnap
                    } else {
                        bioValue.text = "No Bio Yet"
                    }
                    if (dataSnapshot.child(uid.toString()).child("GitHub Link").exists()) {
                        gitValue.text = "GitHub Link: " + gitLink
                    } else {
                        gitValue.text = "No GitHub Link Yet"
                    }
                    if (dataSnapshot.child(uid.toString()).child("College Email").exists()) {
                        emailValue.text = "Contact: " + collegeEmailSnap
                    } else {
                        emailValue.text = "Contact: " + email
                    }

                    profileTitle.visibility=View.VISIBLE
                    bioValue.visibility=View.VISIBLE
                    gitValue.visibility=View.VISIBLE
                    emailValue.visibility=View.VISIBLE
                    emailText.text = "Email: \n" + email
                    userID.text = "User ID: \n" + uid
                    emailText.visibility= View.GONE
                    userID.visibility=View.GONE
                    hideInfoButton.visibility=View.GONE
                    copyIDButton.visibility=View.GONE

                    showInfoButton.setOnClickListener {
                        emailText.visibility= View.VISIBLE
                        userID.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.VISIBLE
                        showInfoButton.visibility=View.GONE
                        profilePicture.visibility = View.VISIBLE
                        copyIDButton.visibility=View.VISIBLE
                    }
                    hideInfoButton.setOnClickListener {
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                    }

                    //submission onclick verify that provided info is valid, and passed to database
                    submit.setOnClickListener {
                        val newName = nameText.getText().toString()
                        val validName = nameCheck(newName)
                        if (validName == true) {
                            DataActivity.nameChange(uid, newName)
                            Toast.makeText(
                                applicationContext,
                                "Name Changed Successfully!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Name Must Be At Least 3 Characters",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                    }
                    gitHubSub.setOnClickListener {
                        val newLink = gitHub.getText().toString()
                        val validLink = linkCheck(newLink)
                        if (validLink == true) {
                            DataActivity.gitHubLink(uid, newLink)
                            Toast.makeText(
                                applicationContext,
                                "Link Added Succesfully!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Not a Valid GitHub Link",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                    }
                    bioSub.setOnClickListener {
                        val newBio = bio.getText().toString()
                        val validBio = bioCheck(newBio)
                        if (validBio == true) {
                            DataActivity.bioAdd(uid, newBio)
                            Toast.makeText(
                                applicationContext,
                                "New Bio Added!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Bio Cannot Be Empty!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
                    }
                    emailSub.setOnClickListener {
                        val collegeEmail = collegeEmail.getText().toString()
                        val validEmail = emailCheck(collegeEmail)
                        if (validEmail == true) {
                            DataActivity.collegeEmail(uid, collegeEmail)
                            Toast.makeText(
                                applicationContext,
                                "Email Added Successfully!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Not a Valid College Email",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        emailText.visibility= View.GONE
                        userID.visibility=View.GONE
                        showInfoButton.visibility=View.VISIBLE
                        hideInfoButton.visibility=View.GONE
                        copyIDButton.visibility=View.GONE
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

                    //launches links as activity
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

        //starts activities onclick
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
    }

    //companion object holds all verification for data from user input
    companion object {
        fun bioCheck(bio: String): Boolean {
            if (bio.trim().isNotBlank()) {
                return true
            }
            return false
        }

        fun emailCheck(email: String): Any {
            if (email.contains(".edu") and email.contains("@")) {
                return true
            }
            return false
        }

        fun nameCheck(newName: String): Any {
            if (newName.length >= 3) {
                return true
            }
            return false
        }

        fun linkCheck(newLink: String): Any {
            if (newLink.contains("github")) {
                return true
            }
            return false
        }
    }
}