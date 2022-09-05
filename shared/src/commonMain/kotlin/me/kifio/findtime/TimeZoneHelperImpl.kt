package me.kifio.findtime

import kotlinx.datetime.*
import kotlin.math.abs

class TimeZoneHelperImpl : TimeZoneHelper {

    override fun getTimeZoneStrings(): List<String> = TimeZone.availableZoneIds.sorted()

    override fun formatDateTime(dateTime: LocalDateTime): String = with(StringBuilder()) {
        append(if (dateTime.hour < 12) dateTime.hour else dateTime.hour - 12)
        append(":")
        if (dateTime.minute < 10) append(0)
        append(dateTime.minute)
        append(if (dateTime.hour > 12) " am" else " pm")
        return@with toString()
    }

    override fun currentTime(): String = formatDateTime(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    )

    override fun hoursFromTimeZone(otherTimeZoneId: String): Double {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentUTCInstant = Clock.System.now()
        val otherTimeZone = TimeZone.of(otherTimeZoneId)
        val currentDateTime = currentUTCInstant.toLocalDateTime(currentTimeZone)
        val currentOtherTime = currentUTCInstant.toLocalDateTime(otherTimeZone)
        return abs((currentDateTime.hour - currentOtherTime.hour) * 1.0)
    }

    override fun getTime(timeZoneID: String): String = formatDateTime(
        Clock.System.now().toLocalDateTime(TimeZone.of(timeZoneID))
    )

    override fun getDate(timeZoneID: String): String {
        val dateTime: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.of(timeZoneID))
        return "${
            dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        }, ${
            dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
        } ${dateTime.date.dayOfMonth}"
    }

    override fun search(strHour: Int, endHour: Int, timeZoneStrings: List<String>): List<Int> {
        TODO("Not yet implemented")
    }

}