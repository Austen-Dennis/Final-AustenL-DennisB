package finals.project

import finals.project.activities.HomeActivity
import junit.framework.TestCase.assertTrue
import org.junit.Test

class HomeActivityTest {
    @Test
    fun isReachableTest(){
        val returnValue = HomeActivity.isReachable()
        assertTrue(returnValue as Boolean)
    }
}