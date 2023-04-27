package finals.project

import finals.project.smsPage.chatitems.ChatLogActivity
import finals.project.activities.LatestMessagesActivity
import finals.project.activities.NewMessageActivity
import junit.framework.TestCase
import org.junit.Test

class SMSTest {
    @Test
    fun chatLogIsReachable() {
        val returnValue = ChatLogActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }

    @Test
    fun latestMessageIsReachableTest() {
        val returnValue = LatestMessagesActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }

    @Test
    fun newMessageIsReachableTest() {
        val returnValue = NewMessageActivity.isReachable()
        TestCase.assertTrue(returnValue as Boolean)
    }
}