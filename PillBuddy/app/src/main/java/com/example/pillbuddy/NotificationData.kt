package com.example.pillbuddy

import com.google.gson.annotations.SerializedName

data class NotificationData (
    @SerializedName("NotifId")
    var NotifId: Int = 0,
    @SerializedName("UserfId")
    var Userid: String? = null,
    @SerializedName("MedicationName")
    var MedicationName: String? = null,
    @SerializedName("Dosage")
    var Dosage: String? = null,
    @SerializedName("Hour")
    var Hour: Int = 0,
    @SerializedName("Minute")
    var Minute: Int = 0,
    //daily = every day
    @SerializedName("Days")
    var Days: Array<String?>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationData

        if (NotifId != other.NotifId) return false
        if (Userid != other.Userid) return false
        if (MedicationName != other.MedicationName) return false
        if (Dosage != other.Dosage) return false
        if (Hour != other.Hour) return false
        if (Minute != other.Minute) return false
        if (Days != null) {
            if (other.Days == null) return false
            if (!Days!!.contentEquals(other.Days!!)) return false
            //if (!(Days as Array).contentEquals(other.Days as Array<out String>)) return false
        } else if (other.Days != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = NotifId
        result = 31 * result + (Userid?.hashCode() ?: 0)
        result = 31 * result + (MedicationName?.hashCode() ?: 0)
        result = 31 * result + (Dosage?.hashCode() ?: 0)
        result = 31 * result + Hour
        result = 31 * result + Minute
        result = 31 * result + (Days?.contentHashCode() ?: 0)
        return result
    }
}


