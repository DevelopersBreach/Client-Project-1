package com.developersbreach.clientproject.view.dashboard

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController


@BindingAdapter("bindDashboardSettingsIconListener")
fun ImageView.setDashboardSettingsIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigate(
            DashboardFragmentDirections.dashboardToSettingsFragment()
        )
    }
}
