package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import finals.project.R

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)


        supportActionBar?.title = "Select User"


    }
}