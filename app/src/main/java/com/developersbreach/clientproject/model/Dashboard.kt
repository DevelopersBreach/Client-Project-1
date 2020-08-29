package com.developersbreach.clientproject.model

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.developersbreach.clientproject.R


class Dashboard(
    val dashboardId: Int,
    val dashboardIcon: Int,
    val dashboardTitle: String,
    val gradientBackground: Int
) {
    object DiffCallBack : DiffUtil.ItemCallback<Dashboard>() {

        override fun areItemsTheSame(
            oldItem: Dashboard,
            newItem: Dashboard
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Dashboard,
            newItem: Dashboard
        ): Boolean {
            return oldItem.dashboardId == newItem.dashboardId
        }
    }

    companion object {

        fun dashboardData(context: Context): List<Dashboard> {

            return listOf(
                Dashboard(
                    0,
                    R.drawable.ic_bill_number,
                    context.getString(R.string.dashboard_item_search_bill_number),
                    R.drawable.contact_gradient
                ),
                Dashboard(
                    1,
                    R.drawable.ic_contact,
                    context.getString(R.string.dashboard_item_contact_shop),
                    R.drawable.bill_number_gradient
                ),
                Dashboard(
                    2,
                    R.drawable.ic_shopping_bag,
                    context.getString(R.string.dashboard_item_services),
                    R.drawable.services_gradient
                )
            )
        }
    }
}

