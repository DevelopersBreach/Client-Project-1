package com.developersbreach.clientproject.view.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.model.Account

class AccountViewModel(
    application: Application,
    val account: Account
) : AndroidViewModel(application)