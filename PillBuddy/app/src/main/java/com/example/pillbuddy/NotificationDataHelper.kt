package com.example.pillbuddy
import android.content.Context
import com.example.pillbuddy.NotificationData

object NotificationDataHelper {
    var notificationList = MutableList(20) {NotificationData()}

    //helper functions
    //findbyid
    fun findNotificationById (id: Int) :NotificationData?{
        for (I in notificationList){
            if (I.NotifId == id){
                return I
            }
        }
        return null
    }
    //schedule the required system alarms for all notifications in the list
    fun scheduleAllAlarms(context: Context){
        for (notifications in notificationList){
            AlarmHandler.scheduleAlarmsForNotification(context, notifications)
        }
    }

    //deletealarms
}