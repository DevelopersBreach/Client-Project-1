package com.developersbreach.clientproject.utils

import com.firebase.ui.auth.AuthUI


const val COLLECTION_PATH: String = "customers"
const val FIELD_MAIL: String = "email"

const val COLLECTION_PATH_PHOTOS: String = "shop"
const val DOCUMENT_IMAGES: String = "images"
const val FIELD_ARRAY_IMAGES: String = "images_array"

const val DELIVERY_STATUS_PENDING = 0
const val DELIVERY_STATUS_COMPLETED = 1

const val PREFERENCE_KEY_USER_LOGOUT: String = "UserLogoutPreferenceKey"
const val PREFERENCE_KEY_ABOUT_APP: String = "AboutAppPreferenceKey"

const val SIGN_IN_REQUEST_CODE = 9001
val PROVIDERS = arrayListOf(
    AuthUI.IdpConfig.GoogleBuilder().build(),
    AuthUI.IdpConfig.EmailBuilder().build()
)