package me.kifio.findtime

import io.github.aakira.napier.Napier
import kotlinx.datetime.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Duration.Companion.hours

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

    override fun currentTimeZone(): String = TimeZone.currentSystemDefault().toString()

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

    override fun search(startHour: Int, endHour: Int, timeZoneStrings: List<String>): List<Int> {
        val goodHours = mutableListOf<Int>()
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        val currentTimezone = TimeZone.currentSystemDefault()

        for (hour in timeRange) {
            var isGoodHour = false
            for (zone in timeZoneStrings) {
                val timeZone = TimeZone.of(zone)

                if (timeZone == currentTimezone) {
                    continue
                }

                if (!isValid(timeRange = timeRange, hour = hour, currentTimezone = currentTimezone, otherTimeZone = timeZone)) {
                    Napier.d("Hour $hour is not valid for time range")
                    isGoodHour = false
                    break
                } else {
                    Napier.d("Hour $hour is valid for time range")
                    isGoodHour = true
                }
            }

            if (isGoodHour) {
                goodHours.add(hour)
            }
        }

        return goodHours
    }

    private fun isValid(
        timeRange: IntRange,
        hour: Int,
        currentTimezone: TimeZone,
        otherTimeZone: TimeZone
    ): Boolean {
        if (hour !in timeRange) {
            return false
        }

        val currentUTCInstant: Instant = Clock.System.now()

        val currentOtherDateTime = currentUTCInstant.toLocalDateTime(otherTimeZone)

        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour,
            0,
            0,
            0
        )

        val localInstant = otherDateTimeWithHour.toInstant(currentTimezone)

        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)

        Napier.d("Hour $hour in Time Range ${otherTimeZone.id} is ${convertedTime.hour}")
        return convertedTime.hour in timeRange
    }

}