package com.example.gallery.ViewModel

import android.content.ContentResolver
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore.Images
import android.provider.MediaStore.Video
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.Model.MediaSection
import com.example.gallery.Repository.GalleryContentObserver
import com.example.gallery.Repository.MediaRepository
import kotlinx.coroutines.launch


class HomeViewModel(val contentResolver: ContentResolver): ViewModel() {
    private val _mediaList = MutableLiveData<MutableList<MediaSection>>(mutableListOf())
    val mediaList: LiveData<MutableList<MediaSection>> = _mediaList
    private var contentObserver =  GalleryContentObserver(Handler(Looper.getMainLooper())) {
        refreshMediaList()
    }

    private val mediaRepository = MediaRepository()

    init {
        refreshMediaList()
        contentResolver.registerContentObserver(
            Images.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
        contentResolver.registerContentObserver(
            Video.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
    }

    private fun refreshMediaList() {
        viewModelScope.launch {
            _mediaList.value = mediaRepository.loadMedia(contentResolver)
        }
    }

    override fun onCleared() {
        super.onCleared()
        contentResolver.unregisterContentObserver(contentObserver)
    }
}