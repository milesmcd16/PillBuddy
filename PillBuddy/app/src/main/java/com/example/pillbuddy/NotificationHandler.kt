package com.example.pillbuddy

import android.os.Build
import android.content.Intent
import android.content.Context
import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.ClipDescription
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat

object NotificationHandler {

    // If the version of Android is late enough, we can't send any notifications without
    // creating at least one channel for the app to send them through.
    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean,
                                  name: String, description: String) {

        // We need to create the notification channel but we need to make sure
        // that we're in a version of Android that supports it first. Older
        // versions didn't require this and won't know what to do with it.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = "${context.packageName}-${context.getString(R.string.app_name)}"
            val channel = NotificationChannel(channelID, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // Registering the channel
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createDemoNotification(context: Context, title: String, text: String,
                               bigText: String, autoCancel: Boolean) {

        // Get channel ID. Should be the same as the channel we created.
        val channelID = "${context.packageName}-${context.getString(R.string.app_name)}"

        // Instantiate the notification builder and build the notification
        val notificationBuilder = NotificationCompat.Builder(context, channelID).apply{
            // Set notification icon. This is required.
            setSmallIcon(R.drawable.ic_launcher_background)
            // Set the notification title
            setContentTitle(title)
            // Set the main text for the notification
            setContentText(text)
            // The big text notification style allows the notification to be expanded and display
            // more text. This sets the style and then enters the extra text for when expanded.
            setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            // If we leave autoCancel on, the notification will simply close if the user touches it.
            setAutoCancel(autoCancel)

            // Set up the notification's intent. This will launch our main menu.
            val intent = Intent(context, HomePage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(context,  0, intent, 0)
            setContentIntent(pendingIntent)
        }

        // Tell the notification manager what we did.
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1001, notificationBuilder.build())
    }

    /*

    **** Not implemented yet ****

    // Build a notification from user entered information
    fun buildNotification(context: Context, reminderContents: String) : NotificationCompat.Builder{
        // This is placeholder.

        // Right now we're just using the createDemoNotification function.
        // In the future this would be similar but use data from the app to populate the fields
        // of the notification.
        val channelID = "${context.packageName}-${context.getString(R.string.app_name)}"

        return NotificationCompat.Builder(context, channelID).apply{

        }
    }

     */
}