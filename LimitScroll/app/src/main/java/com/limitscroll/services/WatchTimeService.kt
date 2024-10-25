package com.limitscroll.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WatchTimeService : Service() {

    companion object {
        var instagramReelsWatchTime = 0
        var youtubeShortsWatchTime = 0
        var snapChatSpotlightsWatchTime = 0

    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

}