package com.developersbreach.clientproject.model

import androidx.recyclerview.widget.DiffUtil

class Shop {

    var shopImages: String? = null

    constructor()

    internal constructor(
        shopImages: String
    ) : this() {
        this.shopImages = shopImages
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Shop>() {

        override fun areItemsTheSame(
            oldItem: Shop,
            newItem: Shop
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Shop,
            newItem: Shop
        ): Boolean {
            return oldItem.shopImages == newItem.shopImages
        }
    }
}