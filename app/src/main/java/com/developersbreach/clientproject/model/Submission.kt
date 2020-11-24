package com.developersbreach.clientproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Submission : Parcelable {

    var billNumber: String? = null
    var delivery: String? = null

    // Requires empty constructor for firestore
    constructor()

    internal constructor(
        billNumber: String,
        delivery: String
    ) : this() {
        this.billNumber = billNumber
        this.delivery = delivery
    }

    private constructor(parcel: Parcel) {
        billNumber = parcel.readString()
        delivery = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(billNumber)
        dest.writeString(delivery)
    }

    companion object CREATOR : Parcelable.Creator<Submission> {
        override fun createFromParcel(parcel: Parcel): Submission {
            return Submission(parcel)
        }

        override fun newArray(size: Int): Array<Submission?> {
            return arrayOfNulls(size)
        }
    }
}