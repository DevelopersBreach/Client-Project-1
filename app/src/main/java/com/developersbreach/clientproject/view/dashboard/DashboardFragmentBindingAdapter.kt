package com.developersbreach.clientproject.view.dashboard

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.model.Dashboard
import com.developersbreach.clientproject.view.controller.dashboardToBillNumber
import com.developersbreach.clientproject.view.controller.dashboardToContact
import com.developersbreach.clientproject.view.controller.dashboardToServices
import com.developersbreach.clientproject.view.controller.dashboardToSettings


@BindingAdapter("bindDashboardListData")
fun RecyclerView.setDashboardListData(
    listData: List<Dashboard>
) {
    val adapter = DashboardAdapter()
    adapter.submitList(listData)
    this.adapter = adapter
}


@BindingAdapter("bindDashboardSettingsIconListener")
fun ImageView.setDashboardSettingsIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        dashboardToSettings(navController)
    }
}


@BindingAdapter("bindDashboardItemImageView")
fun ImageView.setDashboardItemImageView(
    icon: Int
) {
    this.setImageResource(icon)
}


@BindingAdapter("bindDashboardItemParentBackground")
fun ConstraintLayout.setDashboardItemParentBackground(
    dashboard: Dashboard
) {
    this.setBackgroundResource(dashboard.gradientBackground)
    this.setOnClickListener {
        when (dashboard.dashboardId) {
            0 -> dashboardToBillNumber(findNavController())
            1 -> dashboardToContact(findNavController())
            2 -> dashboardToServices(findNavController())
        }
    }
}
