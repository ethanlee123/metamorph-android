package com.example.metamorph.util

import android.util.Log
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
        formatDateTimeBackup(date)
    }
    return date
}

/**
 * @param date pattern must be yyyy-MM-dd'T'HH:mm:ss.S'Z or DateTimeParseException is thrown
 * @throws DateTimeParseException
 */
fun formatDateTimeBackup(date: String): String {
    try {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S'Z'")
        val formattedDate = LocalDate.parse(date, formatter)

        val month = formattedDate.format(DateTimeFormatter.ofPattern("MMM"))
        val day = formattedDate.format(DateTimeFormatter.ofPattern("dd"))
        val year = formattedDate.format(DateTimeFormatter.ofPattern("yyyy"))
        return "$month $day, $year"
    } catch (e: DateTimeParseException) {
        e.message?.let { Log.e("Format exception", it) }
    }
    return date
}