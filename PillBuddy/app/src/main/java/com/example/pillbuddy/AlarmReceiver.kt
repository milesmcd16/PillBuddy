package com.example.pillbuddy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.pillbuddy.NotificationHandler

import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    private val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive() called with: context = [$context], intent = [$intent]")
        if (context != null && intent != null) {
            if (intent.action == "poop") {
                if (intent.extras != null) {
                    val notificationData = NotificationDataHelper.findNotificationById(intent.extras!!.getInt("Notification Id"))
                    if (notificationData != null) {
                        NotificationHandler.createNotification(context, notificationData)

                    }
                }
            }
        }
    }
}