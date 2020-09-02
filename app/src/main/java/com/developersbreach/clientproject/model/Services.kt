package com.developersbreach.clientproject.model

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.developersbreach.clientproject.R

class Services(
    val serviceTitle: String
) {
    object DiffCallBack : DiffUtil.ItemCallback<Services>() {

        override fun areItemsTheSame(
            oldItem: Services,
            newItem: Services
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Services,
            newItem: Services
        ): Boolean {
            return oldItem.serviceTitle == newItem.serviceTitle
        }
    }

    companion object {

        fun servicesData(context: Context): List<Services> {
            return listOf(
                Services(context.getString(R.string.services_alterations)),
                Services(context.getString(R.string.services_blouses)),
                Services(context.getString(R.string.services_dresses)),
                Services(context.getString(R.string.services_uniform)),
                Services(context.getString(R.string.services_wedding)),
                Services(context.getString(R.string.services_women_clothing))
            )
        }
    }
}