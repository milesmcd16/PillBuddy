package com.example.pillbuddy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


/*
    When device boots check which alarms need to be scheduled for that day
 */
class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {

            // Reschedule every alarm here
            //DataUtils.scheduleAlarmsForData(context)
        }
    }
}