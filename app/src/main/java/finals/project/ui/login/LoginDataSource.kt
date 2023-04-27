package finals.project.ui.login

import finals.project.data.Result
import finals.project.activities.LoggedInUser
import java.io.IOException
import java.util.UUID
class LoginDataSource {
    fun login(username: String): Result<LoggedInUser> {
        return try {
            //handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), username)
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }
}