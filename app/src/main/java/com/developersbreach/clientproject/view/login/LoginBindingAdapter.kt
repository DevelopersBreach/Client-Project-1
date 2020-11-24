package com.developersbreach.clientproject.view.login

import android.view.View
import android.view.animation.Animation
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.utils.SubmissionStatus
import com.developersbreach.clientproject.utils.startCircularEffect
import com.developersbreach.clientproject.utils.viewAnimator
import com.google.android.material.card.MaterialCardView


@BindingAdapter(
    "bindLoginVerificationStatus", "bindLoginVerificationProgress",
    "bindParentVerificationLayout"
)
fun MaterialCardView.setLoginVerificationStatus(
    status: SubmissionStatus?,
    progressBar: ProgressBar,
    verificationParent: ConstraintLayout
) {
    when (status) {
        SubmissionStatus.LOADING -> progressBar.visibility = View.VISIBLE
        SubmissionStatus.COMPLETED -> {
            progressBar.visibility = View.INVISIBLE
            showSuccessCard(verificationParent)
        }
        SubmissionStatus.ERROR -> progressBar.visibility = View.INVISIBLE
    }
}

private fun MaterialCardView.showSuccessCard(
    verificationParent: ConstraintLayout
) {
    this.visibility = View.VISIBLE
    verificationParent.visibility = View.INVISIBLE

    val viewAnimator = viewAnimator(
        context,
        this,
        1500L,
        R.anim.fade_enter_anim
    )

    startCircularEffect(
        this, this.top, this.right
    )

    viewAnimator.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation?) {
            findNavController().navigate(
                LoginFragmentDirections.loginToSubmissions()
            )
        }

        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationRepeat(animation: Animation?) {}
    })
}