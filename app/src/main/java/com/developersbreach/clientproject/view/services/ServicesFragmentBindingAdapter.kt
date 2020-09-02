package com.developersbreach.clientproject.view.services

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.model.Services


@BindingAdapter("bindServicesFragmentNavigationIconListener")
fun Toolbar.setContactFragmentNavigationIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigateUp()
    }
}


@BindingAdapter("bindServicesListData")
fun RecyclerView.setServicesListData(
    listData: List<Services>
) {
    val adapter = ServicesAdapter()
    adapter.submitList(listData)
    this.adapter = adapter
}
