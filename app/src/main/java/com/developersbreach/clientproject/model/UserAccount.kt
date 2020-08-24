package com.developersbreach.clientproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserAccount(
    val username: String,
    val userMail: String,
    val profileUrl: String
) : Parcelable