package finals.project

import finals.project.data.DataActivity
import junit.framework.TestCase
import org.junit.Test

class DataTest {

    @Test
    fun isReachableTest() {
        val returnValue = DataActivity.isReachable()
        TestCase.assertTrue(returnValue)
    }

}