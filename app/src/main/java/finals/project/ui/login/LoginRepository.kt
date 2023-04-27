package finals.project.ui.login

import finals.project.activities.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import finals.project.data.Result
class LoginRepository(val dataSource: LoginDataSource) {
    var user: LoggedInUser? = null
        private set

    //creates user/signs in to user if one already exists for provided email
    fun login(username: String, password: String): Result<LoggedInUser> {
        val result = dataSource.login(username)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }

}