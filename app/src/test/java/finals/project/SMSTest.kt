package finals.project

import finals.project.smsPage.ChatLogActivity
import finals.project.smsPage.LatestMessagesActivity
import finals.project.smsPage.NewMessageActivity
import junit.framework.TestCase
import org.junit.Test

class SMSTest {
    @Test
    fun ChatLogIsReachable(){
        val returnValue = ChatLogActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
    @Test
    fun LatestMessageIsReachableTest(){
        val returnValue = LatestMessagesActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
    @Test
    fun NewMessageisReachableTest(){
        val returnValue = NewMessageActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
}