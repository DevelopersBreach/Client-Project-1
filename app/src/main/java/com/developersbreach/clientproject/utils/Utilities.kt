package com.developersbreach.clientproject.utils

import android.app.Activity
import android.util.TypedValue
import android.view.View


fun View.convertToDp(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
        .toInt()
}

fun showStatusBar(activity: Activity) {
    @Suppress("DEPRECATION")
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}

fun hideStatusBar(activity: Activity) {
    @Suppress("DEPRECATION")
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}