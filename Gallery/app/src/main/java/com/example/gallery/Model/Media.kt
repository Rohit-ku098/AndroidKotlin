package com.example.gallery.Model

import android.net.Uri

sealed class MediaSection {
    data class DateHeader(val date: String) : MediaSection()
    data class Media(
        val uri: Uri,
        val name: String,
        val size: Long,
        val mimeType: String,
        val dateAdded: Long,
        val isVideo: Boolean,
        var duration: Long = 0
    ) : MediaSection()
}
