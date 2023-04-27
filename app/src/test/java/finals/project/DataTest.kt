package finals.project

import finals.project.data.DataActivity
import junit.framework.TestCase
import org.junit.Test

class DataTest {

    //just tests if companion object is reachable, realtime database cannot be tested due to impossible initialization
    @Test
    fun isReachableTest() {
        val returnValue = DataActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }

}