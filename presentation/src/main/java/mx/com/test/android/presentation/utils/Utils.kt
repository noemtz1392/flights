package mx.com.test.android.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Int.minutesToHoursAndMinutes(): String {
    val format = "%02dh %02dm"
    val realHours = TimeUnit.MINUTES.toHours(this.toLong())
    val realMinutes = TimeUnit.MINUTES.toMinutes(this.toLong()) - TimeUnit.HOURS.toMinutes(
        TimeUnit.MINUTES.toHours(this.toLong())
    )
    return String.format(format, realHours, realMinutes)
}

fun String.dateToHoursAndMinutes(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
        val parsedDate = inputFormat.parse(this)
        return outputFormat.format(parsedDate as Date)
    } catch (e: Exception) {
        this
    }
}