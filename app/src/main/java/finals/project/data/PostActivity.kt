package finals.project.data

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import finals.project.R
import finals.project.smsPage.SmsPage
import java.text.SimpleDateFormat
import java.util.*

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        val PostImagesReference = FirebaseStorage.getInstance().getReference().child("Post Images");
        val intentSMS = Intent(this, SmsPage::class.java)
        val intentHOME = Intent(this, HomeActivity::class.java)
        val intentCAMERA = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val intentGALLERY = Intent(MediaStore.ACTION_PICK_IMAGES)
        val imageView = findViewById<View>(R.id.imageview1)
        val smsButton = findViewById<View>(R.id.sms)
        smsButton.setOnClickListener {
            startActivity(intentSMS)
        }
        val homeButton = findViewById<View>(R.id.home)
        homeButton.setOnClickListener {
            startActivity(intentHOME)
        }
        val cameraButton = findViewById<View>(R.id.Camera)
        cameraButton.setOnClickListener {
            startActivity(intentCAMERA)
        }
        val galleryButton = findViewById<View>(R.id.Gallery)
        galleryButton.setOnClickListener {
            startActivity(intentGALLERY)
        }
        fun StoringImageToFirebase() {
            val calFordDate = Calendar.getInstance()
            val currentDate = SimpleDateFormat("dd-mmmm-yyyy")
            val saveCurrentDate = currentDate.format(calFordDate.time)

            val calFordTime = Calendar.getInstance()
            val currentTime = SimpleDateFormat("hh-mm")
            val saveCurrentTime = currentTime.format(calFordTime.time)

            val postRandomName = saveCurrentDate+saveCurrentTime
            val storageReference = FirebaseDatabase.getInstance()
        }
    }
    companion object {
        fun iscreated(): Any {
            val created = true;
            return created
        }
    }
}