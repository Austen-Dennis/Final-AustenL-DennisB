package finals.project

import finals.project.ui.login.LoginActivity
import finals.project.ui.login.LoginViewModel
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*


class LoginTest {
    @Test
    fun emailIsNotValid() {
        val emailAddress = "Womper"
        assertFalse(LoginViewModel.isUserNameValid(emailAddress))
    }

    @Test
    fun emailIsValid() {
        val emailAddress = "Womper@gmail.com"
        assertTrue(LoginViewModel.isUserNameValid(emailAddress))
    }

    @Test
    fun emailIsStillValid() {
        val emailAddress = "Womper@hotmail.com"
        assertTrue(LoginViewModel.isUserNameValid(emailAddress))
    }

    @Test
    fun passwordIsNotValid() {
        val password = "womp"
        assertFalse(LoginViewModel.isPasswordValid(password))
    }

    @Test
    fun passwordIsValid() {
        val password = "womper"
        assertTrue(LoginViewModel.isPasswordValid(password))
    }

    @Test
    fun TestIsCreated() {
        val returnValue = LoginActivity.iscreated()
        TestCase.assertTrue(returnValue as Boolean)
    }

    @Test
    fun NameIsTrimmed() {
        val email = "womper@gmail.com"
        val name = LoginActivity.emailTrim(email)
        assertEquals(name, "womper")
    }
}
