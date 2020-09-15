package com.developersbreach.clientproject.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.developersbreach.clientproject.R

class Intro(
        val id: Int,
        val title: String,
        val subtitle: String,
        val banner: Drawable,
        val background: Drawable
) {
    companion object {

        fun addData(context: Context): List<Intro> = introDataSource(context)

        private fun introDataSource(context: Context): List<Intro> = listOf(
                Intro(
                        1,
                        context.getString(R.string.intro_pager_first_title_text),
                        context.getString(R.string.intro_pager_first_subtitle_text),
                        ContextCompat.getDrawable(context, R.drawable.logo)!!,
                        ContextCompat.getDrawable(context, R.drawable.intro_first_gradient)!!
                ),
                Intro(
                        2,
                        context.getString(R.string.intro_pager_second_title_text),
                        context.getString(R.string.intro_pager_second_subtitle_text),
                        ContextCompat.getDrawable(context, R.drawable.ic_bill_number)!!,
                        ContextCompat.getDrawable(context, R.drawable.intro_second_gradient)!!
                ),
                Intro(
                        3,
                        context.getString(R.string.intro_pager_third_title_text),
                        context.getString(R.string.intro_pager_third_subtitle_text),
                        ContextCompat.getDrawable(context, R.drawable.ic_account)!!,
                        ContextCompat.getDrawable(context, R.drawable.intro_third_gradient)!!
                )
        )
    }
}