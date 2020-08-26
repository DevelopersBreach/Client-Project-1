package com.developersbreach.clientproject.utils

import com.firebase.ui.auth.AuthUI


const val COLLECTION_PATH: String = "customers"
const val FIELD_MAIL: String = "email"

const val SIGN_IN_REQUEST_CODE = 9001
val PROVIDERS = arrayListOf(
    AuthUI.IdpConfig.GoogleBuilder().build(),
    AuthUI.IdpConfig.EmailBuilder().build()
)