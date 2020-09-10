package com.developersbreach.clientproject.utils

import android.util.TypedValue
import android.view.View


fun View.convertToDp(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
}