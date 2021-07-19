package com.example.vaccovid19.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

@BindingAdapter("app:progress")
fun ProgressBar.setProgress(testAmount: Float) {
    this.progress = testAmount.toInt()
}
