package com.developersbreach.clientproject.view.contact

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Shop


@BindingAdapter("bindContactListData", "bindContactFragmentReference")
fun RecyclerView.setContactListData(
    liveData: LiveData<List<Shop>>,
    owner: ContactFragment
) {
    liveData.observe(owner, { shopList ->
        val adapter = ShopAdapter(shopList)
        this.adapter = adapter
    })
}


@BindingAdapter("bindContactFragmentNavigationIconListener")
fun Toolbar.setContactFragmentNavigationIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigateUp()
    }
}


@BindingAdapter("bindContactItemImageAndListener")
fun ImageView.setContactItemImage(
    shop: Shop
) {
    Glide
        .with(context)
        .load(shop.shopImages)
        .placeholder(R.drawable.ic_placeholder_image)
        .into(this)

    this.setOnClickListener {
        Toast.makeText(context, "Position", Toast.LENGTH_SHORT).show()
    }
}
