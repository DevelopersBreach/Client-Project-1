package com.developersbreach.clientproject.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemDashboardBinding
import com.developersbreach.clientproject.model.Dashboard
import com.developersbreach.clientproject.view.dashboard.DashboardAdapter.DashboardViewHolder

class DashboardAdapter : ListAdapter<Dashboard, DashboardViewHolder>(Dashboard.DiffCallBack) {

    class DashboardViewHolder(
        private val binding: ItemDashboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            dashboard: Dashboard
        ) {
            binding.dashboard = dashboard
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            ItemDashboardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}