package com.example.gallery.Repository

import android.content.ContentResolver
import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Images
import com.example.gallery.Model.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepository {
    // Run the querying logic in a coroutine outside of the main thread to keep the app responsive.
    // Keep in mind that this code snippet is querying only images of the shared storage.

    suspend fun loadMedia(contentResolver: ContentResolver): MutableList<Media> = withContext(
        Dispatchers.IO) {
        val projection = arrayOf(
            Images.Media._ID,
            Images.Media.DISPLAY_NAME,
            Images.Media.SIZE,
            Images.Media.MIME_TYPE
        )

        val collectionUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Query all the device storage volumes instead of the primary only
            Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            Images.Media.EXTERNAL_CONTENT_URI
        }

        val images = mutableListOf<Media>()

        contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            "${Images.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(Images.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(Images.Media.SIZE)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(Images.Media.MIME_TYPE)

            while (cursor.moveToNext()) {
                val uri = ContentUris.withAppendedId(collectionUri, cursor.getLong(idColumn))
                val name = cursor.getString(displayNameColumn)
                val size = cursor.getLong(sizeColumn)
                val mimeType = cursor.getString(mimeTypeColumn)

                val image = Media(uri, name, size, mimeType)
                images.add(image)
            }
        }

        return@withContext images
    }
}