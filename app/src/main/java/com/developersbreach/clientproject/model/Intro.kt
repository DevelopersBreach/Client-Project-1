package com.developersbreach.clientproject.model

import android.graphics.drawable.Drawable

data class Intro(
        val id: Int,
        val title: String,
        val subtitle: String,
        val banner: Drawable,
        val background: Drawable
)