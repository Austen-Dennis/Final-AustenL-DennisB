package finals.project

import finals.project.data.FriendSearchActivity
import junit.framework.TestCase
import org.junit.Test

class FriendTest {
    @Test
    fun isReachableTest(){
        val returnValue = FriendSearchActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
}