package finals.project

import finals.project.ui.login.LoginViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
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
}
