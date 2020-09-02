package com.developersbreach.clientproject.view.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemServicesBinding
import com.developersbreach.clientproject.model.Services
import com.developersbreach.clientproject.view.services.ServicesAdapter.ServicesViewHolder

class ServicesAdapter : ListAdapter<Services, ServicesViewHolder>(Services.DiffCallBack) {

    class ServicesViewHolder(
        private val binding: ItemServicesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            services: Services
        ) {
            binding.services = services
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        return ServicesViewHolder(
            ItemServicesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}