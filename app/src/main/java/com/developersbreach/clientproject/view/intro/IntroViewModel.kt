package com.developersbreach.clientproject.view.intro

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Intro


class IntroViewModel(application: Application) : AndroidViewModel(application) {

    private var _introListData: List<Intro> = ArrayList()
    var introListData: List<Intro>
        get() = _introListData
        set(value) {
            _introListData = value
        }

    init {
        _introListData = addData(application.applicationContext)
    }

    private fun addData(context: Context): List<Intro> = listOf(
        Intro(
            1,
            context.getString(R.string.intro_pager_first_title_text),
            context.getString(R.string.intro_pager_first_subtitle_text),
            ContextCompat.getDrawable(context, R.drawable.logo)!!,
            ContextCompat.getDrawable(context, R.drawable.gradient_bg)!!
        ),
        Intro(
            2,
            context.getString(R.string.intro_pager_second_title_text),
            context.getString(R.string.intro_pager_second_subtitle_text),
            ContextCompat.getDrawable(context, R.drawable.ic_bill_number)!!,
            ContextCompat.getDrawable(context, R.drawable.services_gradient)!!
        ),
        Intro(
            3,
            context.getString(R.string.intro_pager_third_title_text),
            context.getString(R.string.intro_pager_third_subtitle_text),
            ContextCompat.getDrawable(context, R.drawable.ic_account)!!,
            ContextCompat.getDrawable(context, R.drawable.contact_gradient)!!
        ),
        Intro(
            4,
            context.getString(R.string.intro_pager_fourth_title_text),
            context.getString(R.string.intro_pager_fourth_subtitle_text),
            ContextCompat.getDrawable(context, R.drawable.ic_login)!!,
            ContextCompat.getDrawable(context, R.drawable.bill_number_gradient)!!
        )
    )

}