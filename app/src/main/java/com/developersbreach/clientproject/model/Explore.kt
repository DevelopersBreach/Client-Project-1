package com.developersbreach.clientproject.model

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.developersbreach.clientproject.R

data class Explore(
    val id: Int,
    val icon: Int,
    val title: String
) {

    object DiffCallBack : DiffUtil.ItemCallback<Explore>() {

        override fun areItemsTheSame(
            oldItem: Explore,
            newItem: Explore
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Explore,
            newItem: Explore
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    companion object {

        fun getExploreList(context: Context): List<Explore> = listOf(
            Explore(1, R.drawable.ic_bill_number, context.getString(R.string.list_item_search_bill_number)),
            Explore(2, R.drawable.ic_achievements, context.getString(R.string.list_item_achievements)),
            Explore(3, R.drawable.ic_services, context.getString(R.string.list_item_services)),
            Explore(4, R.drawable.ic_contact, context.getString(R.string.list_item_contact)),
            Explore(5, R.drawable.ic_about, context.getString(R.string.list_item_about)),
        )
    }
}