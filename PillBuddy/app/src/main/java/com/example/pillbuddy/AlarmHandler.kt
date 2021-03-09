package com.example.pillbuddy
// schedule alarms to wake the system up when it needs to create a notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*
import java.util.Calendar.*


object AlarmHandler {

    //schedules an alarm to fire for the notification passed in the parameter
    fun scheduleAlarmsForNotification(context: Context, notificationData: NotificationData){
        //create an alarmmanager object using android systems
        val alarmMngr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if(notificationData.Days != null){
            for (index in notificationData.Days!!.indices){
                val dayToSchedule = notificationData.Days!![index]
                if (dayToSchedule != null){
                    val alarmIntent = createPendingIntent(context, notificationData, dayToSchedule)

                    var dayInt = 0
                    if(dayToSchedule == "monday"){
                         dayInt = 1
                    }
                    if(dayToSchedule == "tuesday"){
                         dayInt = 2
                    }
                    if(dayToSchedule == "wednesday"){
                         dayInt = 3
                    }
                    if(dayToSchedule == "thursday"){
                         dayInt = 4
                    }
                    if(dayToSchedule == "friday"){
                         dayInt = 5
                    }
                    if(dayToSchedule == "saturday"){
                         dayInt = 6
                    }
                    if(dayToSchedule == "sunday"){
                         dayInt = 7
                    }
                    scheduleAlarm(alarmIntent,notificationData , dayInt, alarmMngr)
                }
            }
        }

    }

    // creates a pending intent for the alarm  for the notification data passed in
    private fun createPendingIntent(context: Context, notificationData: NotificationData, day: String?) : PendingIntent? {
        //create the intent
        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).apply {
            type = "$day-${notificationData.MedicationName}"
            putExtra("Notification Id", notificationData.NotifId)
        }
        //return the pending intent
        return PendingIntent.getBroadcast(context, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    // schedule an alarm for a single day
    private fun scheduleAlarm(intent: PendingIntent?, notificationData: NotificationData, day: Int, alarmManager: AlarmManager){
        val alarmData = Calendar.getInstance(Locale.ENGLISH)
        alarmData.timeInMillis = System.currentTimeMillis()
        alarmData.set(MILLISECOND, 0)
        alarmData.set(SECOND, 0)
        alarmData.set(MINUTE, notificationData.Minute)
        alarmData.set(HOUR_OF_DAY, notificationData.Hour)
        alarmData.set(DAY_OF_WEEK, day)

        //determine if the alarm should go off now and
        val today = Calendar.getInstance(Locale.getDefault())

        if(today.get(DAY_OF_WEEK) == day && today.get(HOUR_OF_DAY) == alarmData.get(HOUR_OF_DAY) && today.get(MINUTE) == alarmData.get (MINUTE)){
            //schedule alarm
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmData.timeInMillis, (1000 * 60 * 24 * 7).toLong(), intent)
        }
        //schedule the alarm for exactly one week later
        alarmData.roll(WEEK_OF_YEAR, 1)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmData.timeInMillis, (1000 * 60 * 24 * 7).toLong(), intent)
    }
}