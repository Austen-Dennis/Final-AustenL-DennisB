package finals.project

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import finals.project.data.DataActivity
import finals.project.data.HomeActivity
import junit.framework.TestCase
import org.junit.Test

class DataTest {

    @Test

    fun isCreatedTest(){
        FirebaseApp.clearInstancesForTest()
        val returnValue = DataActivity.userGrab("AV8m3QqFqRXd62ByCzkIiCcdV0Y2")
        TestCase.assertEquals("",returnValue)
    }

}