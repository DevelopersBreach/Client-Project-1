package com.developersbreach.clientproject.view.settings

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController


@BindingAdapter("bindSettingsFragmentNavigationIconListener")
fun Toolbar.setSettingsFragmentNavigationIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigateUp()
    }
}