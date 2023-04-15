package finals.project

import junit.framework.TestCase
import org.junit.Test

class PostActivityTest {
    @Test
    fun isReachableTest(){
        val returnValue = PostActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
}