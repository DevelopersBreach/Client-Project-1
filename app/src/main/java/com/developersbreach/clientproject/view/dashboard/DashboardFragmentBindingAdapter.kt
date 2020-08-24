package com.developersbreach.clientproject.view.dashboard

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.developersbreach.clientproject.R


@BindingAdapter("bindDashboardSettingsIconListener")
fun ImageView.setDashboardSettingsIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigate(R.id.dashboardToSettingsFragment)
    }
}
