package finals.project.smsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import finals.project.R
import finals.project.data.FriendSearchActivity

class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        val name = intent.getStringExtra(FriendSearchActivity.USER_KEY)
        val uid = intent.getStringExtra(FriendSearchActivity.ID)
        supportActionBar?.title = name
        System.out.println(name + " " + uid)

    }
}