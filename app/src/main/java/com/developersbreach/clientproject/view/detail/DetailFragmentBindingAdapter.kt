package com.developersbreach.clientproject.view.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


//@BindingAdapter("bindDetailProfileImage")
//fun ImageView.setDetailProfileImage(
//    photoUrl: String
//) {
//    Glide.with(context)
//        .load(photoUrl)
//        .circleCrop()
//        .into(this)
//}


//@BindingAdapter("bindDetailDateGivenTextView")
//fun TextView.setDetailDateGivenTextView(
//    dateGiven: String
//) {
//    val formatDate = dateGiven.dropLast(5)
//    this.text = formatDate
//}


@BindingAdapter("bindDetailDateDeliveryTextView")
fun TextView.setDetailDateDeliveryTextView(
    deliveryDate: String
) {
    val formatDate = deliveryDate.dropLast(3)
    this.text = formatDate
}


//@BindingAdapter("bindDetailStatusImageView", "bindDetailStatusTextView")
//fun ImageView.setDetailStatusImageView(
//    submission: Submission,
//    statusTextView: TextView
//) {
//    if (submission != null) {
//        if (submission.status == DELIVERY_STATUS_COMPLETED) {
//            this.setImageResource(R.drawable.ic_completed)
//            statusTextView.text = context.getString(R.string.status_completed)
//        } else if (customers.status == DELIVERY_STATUS_PENDING) {
//            this.setImageResource(R.drawable.ic_pending)
//            statusTextView.text = context.getString(R.string.status_pending)
//        }
//    }
//}
