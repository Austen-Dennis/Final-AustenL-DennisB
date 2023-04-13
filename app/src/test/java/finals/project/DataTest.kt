package finals.project

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import finals.project.data.DataActivity
import finals.project.data.FriendSearchActivity
import finals.project.data.HomeActivity
import junit.framework.TestCase
import org.junit.Test

class DataTest {

    @Test

    fun isReachableTest(){
        val returnValue = DataActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }

}