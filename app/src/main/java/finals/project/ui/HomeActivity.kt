package finals.project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import finals.project.R
import finals.project.smsPage.MainActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val intent = Intent(this, MainActivity::class.java)
        val button = findViewById<View>(R.id.sms)
            button.setOnClickListener {
                startActivity(intent)
            }
    }
}