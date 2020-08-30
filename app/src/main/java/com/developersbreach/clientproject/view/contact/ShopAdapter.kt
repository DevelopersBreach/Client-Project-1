package com.developersbreach.clientproject.view.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemShopBinding
import com.developersbreach.clientproject.model.Shop
import com.developersbreach.clientproject.view.contact.ShopAdapter.ShopViewHolder

class ShopAdapter(
    private val shopList: List<Shop>
) : RecyclerView.Adapter<ShopViewHolder>() {

    class ShopViewHolder(
        private val binding: ItemShopBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            shop: Shop
        ) {
            binding.shop = shop
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
        val shop: Shop = shopList[position]
        holder.bind(shop)
    }

    override fun getItemCount(): Int = shopList.size
}