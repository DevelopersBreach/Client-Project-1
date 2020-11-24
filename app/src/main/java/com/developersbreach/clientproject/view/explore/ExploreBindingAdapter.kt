package com.developersbreach.clientproject.view.explore

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.model.Account
import com.developersbreach.clientproject.model.Explore


@BindingAdapter("bindExploreListData")
fun RecyclerView.setExploreListData(
    fragment: ExploreFragment
) {
    val adapter = ExploreAdapter(fragment)
    adapter.submitList(Explore.getExploreList(context))
    this.adapter = adapter
}


@BindingAdapter("bindExploreItemClickListener", "bindExploreFragmentReference")
fun ConstraintLayout.setExploreItemClickListener(
    explore: Explore,
    fragment: ExploreFragment
) {
    this.setOnClickListener {
        when (explore.id) {
            1 -> findNavController(fragment).navigate(ExploreFragmentDirections.exploreToBillNumber())
            2 -> findNavController(fragment).navigate(ExploreFragmentDirections.exploreToAchievements())
            3 -> findNavController(fragment).navigate(ExploreFragmentDirections.exploreToServices())
            4 -> findNavController(fragment).navigate(ExploreFragmentDirections.exploreToContact())
            5 -> findNavController(fragment).navigate(ExploreFragmentDirections.exploreToAbout())
        }
    }
}


@BindingAdapter("bindUserAccountParentListener", "bindExploreFragmentReference")
fun ConstraintLayout.setUserAccountParentListener(
    account: Account?,
    fragment: ExploreFragment
) {
    this.setOnClickListener {
        findNavController(fragment).navigate(ExploreFragmentDirections.exploreToAccount(account))
    }
}


@BindingAdapter("bindItemExploreImageView")
fun ImageView.setItemExploreImageView(
    explore: Explore
) {
    Glide.with(context).load(explore.icon).into(this)
}