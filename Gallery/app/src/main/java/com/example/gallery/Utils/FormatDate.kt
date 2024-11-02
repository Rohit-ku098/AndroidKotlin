package com.example.gallery.Utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun formatDate(timestamp: Long): String {
    val now = Calendar.getInstance()
    val date = Calendar.getInstance().apply { time = Date(timestamp) }

    val today = Calendar.getInstance()
    val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }

    return when {
        // Check if the date is today
        date.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> "Today"

        // Check if the date is yesterday
        date.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR) -> "Yesterday"

        // Check if the date is in the current year
        date.get(Calendar.YEAR) == now.get(Calendar.YEAR) -> {
            val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
            dateFormat.format(date.time)
        }

        // For dates from previous years
        else -> {
            val dateFormat = SimpleDateFormat("yyyy, MMM dd", Locale.getDefault())
            dateFormat.format(date.time)
        }
    }
}