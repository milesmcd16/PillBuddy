package com.example.pillbuddy

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.example.pillbuddy.NotificationHandler

class PillBuddy : Application() {

    companion object {
        lateinit var instance: PillBuddy
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //create notification channel when app is started
        NotificationHandler.createNotificationChannel(this,
                NotificationManagerCompat.IMPORTANCE_HIGH,true, "Pill Buddy",
                "Default Notification Channel")
    }
}
