package finals.project

import finals.project.activities.HomeActivity
import junit.framework.TestCase
import org.junit.Test

class FriendTest {
    @Test
    fun isReachableTest() {
        val returnValue = HomeActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
}