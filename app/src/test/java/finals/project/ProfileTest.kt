package finals.project

import finals.project.data.ProfileActivity
import junit.framework.TestCase
import org.junit.Test

class ProfileTest {
    @Test
    fun isValidBio(){
        val bio = "WOW!"
        val returnValue = ProfileActivity.bioCheck(bio)
        TestCase.assertTrue(returnValue)
    }
    @Test
    fun isNotValidBio() {
        val bio = ""
        val returnValue = ProfileActivity.bioCheck(bio)
        TestCase.assertFalse(returnValue)
    }
    @Test
    fun isStillNotValidBio() {
        val bio = "        "
        val returnValue = ProfileActivity.bioCheck(bio)
        TestCase.assertFalse(returnValue)
    }
    @Test
    fun isValidEmail(){
        val email = "drbeaver@bsu.edu"
        val returnValue = ProfileActivity.emailCheck(email)
        TestCase.assertTrue(returnValue as Boolean)
    }
    @Test
    fun isNotValidEmail() {
        val email = "drbeaver@gmail.com"
        val returnValue = ProfileActivity.emailCheck(email)
        TestCase.assertFalse(returnValue as Boolean)
    }

    @Test
    fun isValidName(){
        val name = "Ben"
        val returnValue = ProfileActivity.nameCheck(name)
        TestCase.assertTrue(returnValue as Boolean)
    }

    @Test
    fun isNotValidName() {
        val name = "MM"
        val returnValue = ProfileActivity.nameCheck(name)
        TestCase.assertFalse(returnValue as Boolean)
    }
    @Test
    fun isValidLink(){
        val link = "https://github.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR"
        val returnValue = ProfileActivity.linkCheck(link)
        TestCase.assertTrue(returnValue as Boolean)
    }

    @Test
    fun isNotValidLink() {
        val link = "https://hitgub.com/bsu-cs222-spring23-dll/Final-AustenL-DennisB-BeethovenM-JulianR"
        val returnValue = ProfileActivity.linkCheck(link)
        TestCase.assertFalse(returnValue as Boolean)
    }
}