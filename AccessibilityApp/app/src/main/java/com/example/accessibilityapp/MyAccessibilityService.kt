package com.example.accessibilityapp

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast

class MyAccessibilityService: AccessibilityService() {

    companion object {
        const val TAG = "MyAccessibilityService"
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        Log.d(TAG, "On accessibility event:-->> $event ")



        if (event != null && event.packageName != null) {
            val packageName = event.packageName.toString()

//            Log.i(TAG, "Package name:-->> $packageName")
            Log.d(TAG, "View Id: -->> ${event?.source?.viewIdResourceName} and event type: -->> ${event.eventType}")
//            Log.i(TAG, "Content description:-->> ${event?.contentDescription}")
            when (packageName) {
                "com.instagram.android" -> {
                    detectAndBlockInstagramReels(event)
                }
                "com.google.android.youtube" -> {
                    detectAndBlockYouTubeShorts(event)
                }
                "com.snapchat.android" -> {
                    detectAndBlockSnapchatSpotlights(event)
                }
                "com.facebook.katana" -> {
                    detectAndBlockFacebookSpotlights(event)
                }
            }
        }

    }

    override fun onInterrupt() {
        Log.e(TAG, "Something went wrong")
    }

    private val info = AccessibilityServiceInfo()
    override fun onServiceConnected() {
        info.apply {
            eventTypes = AccessibilityEvent.TYPES_ALL_MASK
            packageNames = arrayOf("com.instagram.android", "com.google.android.youtube", "com.snapchat.android", "com.facebook.katana")
            flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
            notificationTimeout = 100
        }
        this.serviceInfo = info
        Log.i(TAG, "onServiceConnected")
    }

    private fun detectAndBlockInstagramReels(event: AccessibilityEvent) {
        val source = event.source ?: return
        if (isReelsUIVisible(source)) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "Instagram Reels Blocked", Toast.LENGTH_SHORT).show()
            }
            performBackAction()
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "Instagram Reels Unblocked", Toast.LENGTH_SHORT).show()
            }
            Log.d("MyAccessibilityService", "Reels content detected and blocked!")

        }
    }

    private fun detectAndBlockYouTubeShorts(event: AccessibilityEvent) {
        val source = event.source ?: return
        if(source?.childCount != null) {
            for(i in 0 until source?.childCount!!) {
                val child = source?.getChild(i)
                if(child?.viewIdResourceName?.contains("com.google.android.youtube:id/reel_recycler") == true) {
                    performBackAction()
                    Log.d(TAG, "--->>YouTube Shorts content detected and blocked!")
                }
            }
        }
        if (isShortsUIVisible(source)) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "YouTube Shorts Blocked", Toast.LENGTH_SHORT).show()
            }
            performBackAction()
            Log.d("MyAccessibilityService", "YouTube Shorts content detected and blocked!")
        }
    }

    private fun isReelsUIVisible(nodeInfo: AccessibilityNodeInfo): Boolean {
        // Detect Reels based on specific Instagram UI elements
        if(nodeInfo.viewIdResourceName?.contains("com.instagram.android:id/clips_viewer_view_pager") == true) {
            return true
        }
        return false
    }

    private fun isShortsUIVisible(nodeInfo: AccessibilityNodeInfo): Boolean {
        // Detect YouTube Shorts based on specific UI elements
        if(nodeInfo.viewIdResourceName?.contains("com.google.android.youtube:id/reel_recycler") == true) {
            return true
        }
        return false
    }

    private fun detectAndBlockSnapchatSpotlights(event: AccessibilityEvent) {
        val source = event.source ?: return
        if (source.viewIdResourceName?.contains("com.snapchat.android:id/ngs_spotlight_icon_container") == true) {
            performBackAction()
            Log.d("MyAccessibilityService", "Snapchat Spotlight content detected and blocked!")
        }
    }
    private fun detectAndBlockFacebookSpotlights(event: AccessibilityEvent) {
        val source = event.source ?: return
        Log.d(TAG, "--->> ${event}")
        Log.d(TAG, "--->> source: ${event.source}")
    }
    fun performBackAction() {
        val backAction = AccessibilityService.GLOBAL_ACTION_BACK
        performGlobalAction(backAction)
    }
}

