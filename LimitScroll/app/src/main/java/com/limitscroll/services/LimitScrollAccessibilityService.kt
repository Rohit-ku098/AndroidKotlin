package com.limitscroll.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class LimitScrollAccessibilityService: AccessibilityService() {
    companion object {
        const val TAG = "MyAccessibilityService"
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        Log.d(TAG, "On accessibility event:-->> $event ")

        event?.let {
            val packageName = event.packageName?.toString()

//            Log.i(TAG, "Package name:-->> $packageName")
//            Log.d(TAG, "View Id: -->> ${event?.source?.viewIdResourceName} and event type: -->> ${event.eventType}")
//            Log.i(TAG, "Content description:-->> ${event?.contentDescription}")
            when (packageName) {
                "com.instagram.android" -> detectAndBlockInstagramReels(event)
                "com.google.android.youtube" -> detectAndBlockYouTubeShorts(event)
                "com.snapchat.android" -> detectAndBlockSnapchatSpotlights(event.source)
                "com.facebook.katana" -> detectAndBlockFacebookSpotlights(event)
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
        if (source.viewIdResourceName?.contains("com.instagram.android:id/clips_viewer_view_pager") == true) {
            performBackAction()
            Log.d(TAG, "Instagram Reels content detected and blocked!")

        }
    }

    private fun detectAndBlockYouTubeShorts(event: AccessibilityEvent) {
        val source = event.source ?: return
        if(source?.childCount != null) {
            for(i in 0 until source?.childCount!!) {
                val child = source?.getChild(i)
                if(child?.viewIdResourceName?.contains("com.google.android.youtube:id/reel_recycler") == true) {
                    performBackAction()
                    Log.d(TAG, "YouTube Shorts content detected and blocked!")
                }
            }
        }
        if (source.viewIdResourceName?.contains("com.google.android.youtube:id/reel_recycler") == true) {
            performBackAction()
            Log.d(TAG, "YouTube Shorts content detected and blocked!")
        }
    }


    private fun detectAndBlockSnapchatSpotlights(source: AccessibilityNodeInfo?) {
        if(source == null) return
        val childCount:Int = source?.childCount ?: 0
        if(source?.viewIdResourceName?.contains("com.snapchat.android:id/spotlight_view_count") == true) {
            Log.i(TAG, "recursion Snapchat Spotlight content detected and blocked!")
            performBackAction()
            return
        }
        if(childCount > 0) {
            for(i in 0 until childCount) {
                val child = source?.getChild(i)
                if (child != null) {
                    detectAndBlockSnapchatSpotlights(child)
                }

            }
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