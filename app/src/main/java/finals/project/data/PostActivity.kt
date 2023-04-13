package finals.project.data

import com.google.firebase.database.FirebaseDatabase
import finals.project.smsPage.LatestMessagesActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.annotation.SuppressLint
import android.provider.MediaStore
import java.text.SimpleDateFormat
import android.content.Intent
import android.app.Activity
import android.view.View
import android.os.Bundle
import android.net.Uri
import java.util.*


@Suppress("DEPRECATION")
class PostActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finals.project.R.layout.post)

        //initial variables for setting intents.
        val toolbar = findViewById<View>(io.getstream.chat.android.ui.R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        val intentSMS = Intent(this, LatestMessagesActivity::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val intentSEARCH = Intent(this, FriendSearchActivity::class.java)
        val intentPROFILE = Intent(this, ProfileActivity::class.java)
        val intentCAMERA = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val intentGALLERY = Intent(MediaStore.ACTION_PICK_IMAGES)
        val imgView = findViewById<View>(finals.project.R.id.imageview1)
        val smsButton = findViewById<View>(finals.project.R.id.sms)

        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val homeButton = findViewById<View>(finals.project.R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }
        val cameraButton = findViewById<View>(finals.project.R.id.Camera)
        cameraButton.setOnClickListener {
            startActivity(intentCAMERA)
        }
        val galleryButton = findViewById<View>(finals.project.R.id.Gallery)
        galleryButton.setOnClickListener {
            startActivity(intentGALLERY)
        }
        val searchButton = findViewById<View>(finals.project.R.id.search)
        searchButton.setOnClickListener {
            startActivity(intentSEARCH)
        }
        val profileButton = findViewById<View>(finals.project.R.id.profile)
        profileButton.setOnClickListener {
            startActivity(intentPROFILE)
        }

        //Stores images in the firebase database
        fun StoringImageToFirebase() {
            val calFordDate = Calendar.getInstance()
            val currentDate = SimpleDateFormat("dd-mmmm-yyyy")
            val saveCurrentDate = currentDate.format(calFordDate.time)

            val calFordTime = Calendar.getInstance()
            val currentTime = SimpleDateFormat("hh-mm")
            val saveCurrentTime = currentTime.format(calFordTime.time)

            val postRandomName = saveCurrentDate + saveCurrentTime
            val storageReference = FirebaseDatabase.getInstance()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            val selectedImage: Uri? = data!!.getData()
        }
    }

    companion object {
        fun isReachable(): Any {
            val created = true;
            return created
        }
    }
}
