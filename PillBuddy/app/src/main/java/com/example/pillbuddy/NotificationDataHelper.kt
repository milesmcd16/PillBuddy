package com.example.pillbuddy
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
    //schedulealarms

    //deletealarms
}