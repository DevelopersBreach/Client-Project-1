package com.developersbreach.clientproject.view.dashboard

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.model.Dashboard


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
        navController.navigate(
            DashboardFragmentDirections.dashboardToSettingsFragment()
        )
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

            0 -> findNavController().navigate(
                DashboardFragmentDirections.dashboardToBillNumberFragment()
            )

            1 -> findNavController().navigate(
                DashboardFragmentDirections.dashboardToContactFragment()
            )

            2 -> findNavController().navigate(
                DashboardFragmentDirections.dashboardToServicesFragment()
            )
        }
    }
}
