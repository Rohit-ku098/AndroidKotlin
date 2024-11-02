package com.example.gallery.Repository

import android.content.ContentResolver
import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import com.example.gallery.Model.MediaSection
import com.example.gallery.Utils.formatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepository {

    // Function to load images and videos separately and combine them into one list.
    suspend fun loadMedia(contentResolver: ContentResolver): MutableList<MediaSection> = withContext(
        Dispatchers.IO) {

        val images = loadImages(contentResolver)
        val videos = loadVideos(contentResolver)

        // Combine images and videos, sorted by date added in descending order.
        val mediaList = (images + videos).sortedByDescending { it.dateAdded }.toMutableList()

        // Group media by date added
        val mediaGroup = mutableListOf<MediaSection>()

        var lastDate: String? = null
        mediaList.forEach { media ->
            val date = formatDate(media.dateAdded * 1000)
            if(lastDate != date) {
                mediaGroup.add(MediaSection.DateHeader(date))
                lastDate = date
            }
            mediaGroup.add(media)
        }

        return@withContext mediaGroup
    }

    // Function to load images from MediaStore
    suspend fun loadImages(contentResolver: ContentResolver): MutableList<MediaSection.Media> = withContext(
        Dispatchers.IO) {

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATE_ADDED
        )

        val collectionUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val images = mutableListOf<MediaSection.Media>()

        contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(collectionUri, id)
                val name = cursor.getString(displayNameColumn)
                val size = cursor.getLong(sizeColumn)
                val mimeType = cursor.getString(mimeTypeColumn)
                val dateAdded = cursor.getLong(dateAddedColumn)

                images.add(MediaSection.Media(uri, name, size, mimeType, dateAdded, isVideo = false))
            }
        }

        return@withContext images
    }

    // Function to load videos from MediaStore
    suspend fun loadVideos(contentResolver: ContentResolver): MutableList<MediaSection.Media> = withContext(
        Dispatchers.IO) {

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION // Specific to videos
        )

        val collectionUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }

        val videos = mutableListOf<MediaSection.Media>()

        contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            "${MediaStore.Video.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(collectionUri, id)
                val name = cursor.getString(displayNameColumn)
                val size = cursor.getLong(sizeColumn)
                val mimeType = cursor.getString(mimeTypeColumn)
                val dateAdded = cursor.getLong(dateAddedColumn)
                val duration = cursor.getLong(durationColumn)

                videos.add(MediaSection.Media(uri, name, size, mimeType, dateAdded, isVideo = true, duration = duration))
            }
        }

        return@withContext videos
    }


}
