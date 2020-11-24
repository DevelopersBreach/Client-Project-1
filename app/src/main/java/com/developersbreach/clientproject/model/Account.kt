package com.developersbreach.clientproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Account : Parcelable {

    var phoneNumber: String? = null
    var username: String? = null
    var email: String? = null
    var photoUrl: String? = null

    constructor(parcel: Parcel) : this() {
        phoneNumber = parcel.readString()
        username = parcel.readString()
        email = parcel.readString()
        photoUrl = parcel.readString()
    }

    internal constructor(
        phoneNumber: String?,
        username: String?,
        email: String?,
        photoUrl: String?
    ) {
        this.phoneNumber = phoneNumber
        this.username = username
        this.email = email
        this.photoUrl = photoUrl
    }

    constructor()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phoneNumber)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(photoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Account> {
        override fun createFromParcel(parcel: Parcel): Account {
            return Account(parcel)
        }

        override fun newArray(size: Int): Array<Account?> {
            return arrayOfNulls(size)
        }
    }
}