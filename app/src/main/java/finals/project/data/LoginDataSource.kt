package finals.project.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import finals.project.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            return Result.Success(fakeUser)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(username.toString(),
                password
            )
            val firebaseUser: FirebaseUser
            /*Toast.makeText(
                applicationContext,
                username.toString(),
                Toast.LENGTH_LONG
            ).show() */
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}