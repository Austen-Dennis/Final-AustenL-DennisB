package finals.project

import finals.project.data.HomeActivity
import junit.framework.TestCase.assertTrue
import org.junit.Test

class HomeActivityTest {
    @Test
    fun isCreatedTest(){
        val returnValue = HomeActivity.iscreated()
        assertTrue(returnValue as Boolean)
    }
}