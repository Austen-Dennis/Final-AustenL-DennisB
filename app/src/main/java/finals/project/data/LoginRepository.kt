package finals.project.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import finals.project.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {
    var user: LoggedInUser? = null
        private set
    val isLoggedIn: Boolean
        get() = user != null
    init {
        user = null
    }
    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val result = dataSource.login(username, password)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
        }
        return result
    }
    fun setLoggedInUser(loggedInUser: LoggedInUser){
        this.user = loggedInUser
    }

}