package com.reciapp.gatherer.extensions.ui

import android.app.Activity
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reciapp.gatherer.R

fun Activity.showLoaderView() {
    if (findViewById<ViewGroup>(R.id.cnlLoading) == null) {
        getContentView().addView(
            LayoutInflater.from(this)
                .inflate(R.layout.view_loading, getContentView(), false).apply {
                    animateFadingIn()
                }
        )
    }
}

fun Activity.hideLoaderView() {
    findViewById<View>(R.id.cnlLoading)?.let { loadingView ->
        loadingView.animateFadingOut {
            getContentView().removeView(loadingView)
        }
    }
}

fun Activity.hideLoaderViewWithDelay() {
    Handler().postDelayed({ hideLoaderView() }, 500L)
}

fun Fragment.showLoaderView() {
    requireActivity().showLoaderView()
}

fun Fragment.hideLoaderView() {
    requireActivity().hideLoaderView()
}

fun Fragment.hideLoaderViewWithDelay() {
    requireActivity().hideLoaderViewWithDelay()
}

private fun Activity.getContentView(): ViewGroup = findViewById(android.R.id.content)
