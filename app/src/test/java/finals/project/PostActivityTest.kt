package finals.project

import finals.project.data.HomeActivity
import finals.project.data.PostActivity
import junit.framework.TestCase
import org.junit.Test

class PostActivityTest {
    @Test
    fun isCreatedTest(){
        val returnValue = PostActivity.iscreated()
        TestCase.assertTrue(returnValue as Boolean)
    }
}