package com.developersbreach.clientproject.view.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Customers


@BindingAdapter("bindDetailFragmentNavigationIconListener")
fun Toolbar.setDetailFragmentNavigationIconListener(
    navController: NavController
) {
    this.setOnClickListener {
        navController.navigateUp()
    }
}


@BindingAdapter("bindDetailProfileImage")
fun ImageView.setDetailProfileImage(
    photoUrl: String
) {
    Glide.with(context)
        .load(photoUrl)
        .circleCrop()
        .into(this)
}


@BindingAdapter("bindDetailDateGivenTextView")
fun TextView.setDetailDateGivenTextView(
    date: String
) {
    val formatDate = date.dropLast(5)
    this.text = formatDate
}


@BindingAdapter("bindDetailStatusImageView", "bindDetailStatusTextView")
fun ImageView.setDetailStatusImageView(
    customers: Customers?,
    statusTextView: TextView
) {
    if (customers != null) {
        if (customers.status) {
            this.setImageResource(R.drawable.ic_completed)
            statusTextView.text = context.getString(R.string.status_completed)
        } else {
            this.setImageResource(R.drawable.ic_pending)
            statusTextView.text = context.getString(R.string.status_pending)
        }
    }
}
