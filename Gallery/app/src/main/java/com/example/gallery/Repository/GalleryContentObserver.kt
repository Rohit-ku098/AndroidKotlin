package com.example.gallery.Repository

import android.database.ContentObserver
import android.os.Handler

class GalleryContentObserver(
    private val handler: Handler,
    private val onChangeDetected: () -> Unit
) : ContentObserver(handler) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        onChangeDetected()
    }
}