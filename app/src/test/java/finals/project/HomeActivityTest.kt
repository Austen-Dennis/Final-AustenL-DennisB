package finals.project

import junit.framework.TestCase.assertTrue
import finals.project.data.HomeActivity
import org.junit.Test

class HomeActivityTest {
    @Test
    fun isReachableTest(){
        val returnValue = HomeActivity.isReachable()
        assertTrue(returnValue as Boolean)
    }
}