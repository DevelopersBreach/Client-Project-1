package com.developersbreach.clientproject.view.login

import android.app.Activity
import android.widget.Button
import androidx.databinding.BindingAdapter
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.utils.PROVIDERS
import com.developersbreach.clientproject.utils.SIGN_IN_REQUEST_CODE
import com.firebase.ui.auth.AuthUI


@BindingAdapter("bindLoginButton")
fun Button.setLoginButton(
    activity: Activity
) {
    this.setOnClickListener {
        activity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(PROVIDERS)
                .setIsSmartLockEnabled(false)
                .setLogo(R.drawable.logo)
                .setTheme(R.style.AppTheme_Customer_Firebase)
                .build(),
            SIGN_IN_REQUEST_CODE
        )
    }
}