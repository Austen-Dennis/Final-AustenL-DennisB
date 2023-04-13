package finals.project.smsPage

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val uid: String, val Name: String, val CollegeEmail: String, val Email: String, val Bio: String, val GitHub: String): Parcelable{
    constructor(): this("","","","","","")
}