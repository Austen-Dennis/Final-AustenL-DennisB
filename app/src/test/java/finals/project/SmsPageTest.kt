package finals.project

import finals.project.data.HomeActivity
import finals.project.smsPage.SmsPage
import junit.framework.TestCase
import org.junit.Test

class SmsPageTest {
    @Test
    fun isCreatedTest(){
        val returnValue = SmsPage.iscreated()
        TestCase.assertTrue(returnValue as Boolean)
    }
}