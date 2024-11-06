package com.example.gallery.Utils

import android.content.res.Resources

// Method to calculate span count
fun calculateSpanCount(resources: Resources, item_width: Int): Int {
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val itemWidth = item_width
    val spanCount = (screenWidth / itemWidth).coerceAtLeast(1)
    return spanCount
}