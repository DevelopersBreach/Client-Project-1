package com.developersbreach.clientproject.view.detail

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Customers


@BindingAdapter("bindDetailProfileImage")
fun ImageView.setDetailProfileImage(
    photoUrl: String
) {
    Log.e("Binding", photoUrl)
    Glide.with(context)
        .load(photoUrl)
        .circleCrop()
        .into(this)
}


@BindingAdapter("bindDetailStatusImageView")
fun ImageView.setDetailStatusImageView(
    customers: Customers?
) {
    if (customers != null) {
        if (customers.status) {
            this.setImageResource(R.drawable.ic_completed)
        } else {
            this.setImageResource(R.drawable.ic_clear)
        }
    }
}
