package com.reciapp.gatherer.extensions.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

const val ANIMATION_DURATION = 50L

/**
 * Animate the loading view to 100% opacity. After the animation ends, set its visibility to VISIBLE as an optimization step (it won't participate in layout passes, etc.)
 */
fun View.animateFadingIn(duration: Long = ANIMATION_DURATION) {
    visibility = View.VISIBLE
    alpha = 0f
    animate()
        .alpha(1f)
        .setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.VISIBLE
            }
        })
}

/**
 * Animate the loading view to 0% opacity. After the animation ends, set its visibility to GONE as an optimization step (it won't participate in layout passes, etc.)
 */
fun View.animateFadingOut(
    duration: Long = ANIMATION_DURATION,
    onAnimationEnd: (() -> Unit)? = null
) {
    visibility = View.VISIBLE
    alpha = 1f
    animate()
        .alpha(0f)
        .setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.GONE
                onAnimationEnd?.invoke()
            }
        })
}
