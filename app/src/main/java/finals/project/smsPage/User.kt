package finals.project.smsPage

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
class User(val uid: String, val Name: String, val CollegeEmail: String, val Email: String, val Bio: String, val GitHub: String, val friendRequest: Boolean): Parcelable{
    constructor(): this("","","","","","", true)
}