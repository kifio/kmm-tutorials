package me.kifio.findtime

import kotlinx.datetime.LocalDateTime

interface TimeZoneHelper {
    fun getTimeZoneStrings(): List<String>
    fun formatDateTime(dateTime: LocalDateTime): String
    fun currentTime(): String
    fun currentTimeZone(): String
    fun hoursFromTimeZone(otherTimeZoneId: String): Double
    fun getTime(timeZoneID: String): String
    fun getDate(timeZoneID: String): String
    fun search(strHour: Int, endHour: Int, timeZoneStrings: List<String>): List<Int>
}