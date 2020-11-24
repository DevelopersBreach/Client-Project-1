package com.developersbreach.clientproject.view.editor

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.clientproject.model.Submission

class EditorViewModelFactory internal constructor(
    private val application: Application,
    private val submission: Submission?
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditorViewModel::class.java)) {
            return EditorViewModel(application, submission) as T
        }
        throw IllegalArgumentException("Cannot create Instance for EditorViewModel class")
    }
}