package com.developersbreach.clientproject.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.model.Account
import com.developersbreach.clientproject.model.Submission

class DetailViewModel(
    application: Application,
    val submission: Submission?,
    val account: Account?
) : AndroidViewModel(application)