package com.developersbreach.clientproject.view.contact

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.databinding.ItemShopBinding
import com.developersbreach.clientproject.model.Shop
import com.developersbreach.clientproject.view.contact.ShopAdapter.ShopViewHolder

class ShopAdapter : ListAdapter<Shop, ShopViewHolder>(Shop.DiffCallBack) {

    class ShopViewHolder(
        private val binding: ItemShopBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            shop: Shop
        ) {
            Log.e("Adapter", shop.shopImages!!)
            Glide.with(binding.itemShopImageView.context).load(shop.shopImages)
                .into(binding.itemShopImageView)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            ItemShopBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}