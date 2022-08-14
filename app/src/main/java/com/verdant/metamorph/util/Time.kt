package com.verdant.metamorph.util

import android.util.Log
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * @param date pattern can be yyyy-MM-dd'T'HH:mm:ss.SSS'Z or yyyy-MM-dd'T'HH:mm:ss.S'Z
 * @return ex. Aug 11, 2022
 */
fun formatDateTime(date: String): String {
    try {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formattedDate = LocalDate.parse(date, formatter)

        val month = formattedDate.format(DateTimeFormatter.ofPattern("MMM"))
        val day = formattedDate.format(DateTimeFormatter.ofPattern("dd"))
        val year = formattedDate.format(DateTimeFormatter.ofPattern("yyyy"))
        return "$month $day, $year"
    } catch (e: DateTimeParseException) {
        e.message?.let { Log.e("Format exception", it) }
    }
    return formatDateTimeBackup(date)
}

/**
 * Hacky solution b/c response is returning an unsupported date pattern.
 * @param date pattern is yyyy-MM-dd'T'HH:mm:ss.S'Z'
 * @throws NumberFormatException if it cannot cast string integer to integer
 */
fun formatDateTimeBackup(date: String): String {
    var year: String? = null
    var monthNumerical: Int? = null
    var day: String? = null
    try {
        year = date.substring(0, 4)
        monthNumerical = date.substring(5, 7).toInt()
        day = date.substring(8, 10)

        // Subtract 1 since Jan is at index 0
        val month = DateFormatSymbols().shortMonths[monthNumerical - 1]
        return "$month $day, $year"
    } catch (e: NumberFormatException) {
        Log.e("NumberFormatExc", e.message.toString())
    }
    return "$monthNumerical $day, $year"
}