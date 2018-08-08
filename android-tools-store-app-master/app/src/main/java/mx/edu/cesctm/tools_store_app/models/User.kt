package mx.edu.cesctm.tools_store_app.models

import kotlin.jvm.java
import android.os.Parcel
import  android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("id") val mId: Int?,
        @SerializedName("email") val mUsername: String?,
        @SerializedName("password") val mFirstName: String?
        //@SerializedName("role") val mLastName: String?,
       //val id: Int?,
       // val email:String?,
        //val password:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeValue(mId)
            dest.writeString(mUsername)
            dest.writeString(mFirstName)
        }
    }

    override fun describeContents(): Int = 0

    fun fullname(): String {
            return " ${this.mUsername} ${this.mFirstName}"

    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}