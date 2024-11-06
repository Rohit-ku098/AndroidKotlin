package com.example.gallery.Model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class MediaSection {
    data class DateHeader(val date: String) : MediaSection()
    @Parcelize
    data class Media(
        val uri: Uri,
        val name: String,
        val size: Long,
        val mimeType: String,
        val dateAdded: Long,
        val isVideo: Boolean,
        var duration: Long = 0
    ) : MediaSection(), Parcelable

    companion object{
        var mediaCount: Int = 0
    }
}
