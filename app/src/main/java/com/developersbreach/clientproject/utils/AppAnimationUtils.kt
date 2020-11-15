package com.developersbreach.clientproject.utils

import android.content.Context
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils

/**
 * @param rootView pass any view to this method for effect. This will create a circular reveal
 * effect for view from left side and bottom of the screen.
 */
fun startCircularEffect(rootView: View, cx: Int, cy: Int) {
    // get the left bottom corner for the clipping circle
    // get the final radius for the clipping circle
    val finalRadius = kotlin.math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
    // create the animator for this view (the start radius is zero)
    val animator = ViewAnimationUtils.createCircularReveal(
        rootView, cx, cy, 0f, finalRadius)
    // Starts the animation.
    animator.start()
}

fun viewAnimator(
    context: Context,
    view: View,
    duration: Long,
    animationProperty: Int
): Animation {
    val animation = AnimationUtils.loadAnimation(context, animationProperty)
    animation.duration = duration
    view.startAnimation(animation)
    return animation
}