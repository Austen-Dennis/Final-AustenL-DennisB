package finals.project.smsPage.userinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(val uid: String, val Name: String, val CollegeEmail: String, val Email: String, val Bio: String, val GitHub: String, val friendRequest: String): Parcelable{
    constructor(): this("","","","","","", "")
}