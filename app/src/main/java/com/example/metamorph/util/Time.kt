package com.example.metamorph.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatDateTime(date: String): String {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val formattedDate = LocalDate.parse(date, formatter)

    val month = formattedDate.format(DateTimeFormatter.ofPattern("MMM"))
    val day = formattedDate.format(DateTimeFormatter.ofPattern("dd"))
    val year = formattedDate.format(DateTimeFormatter.ofPattern("yyyy"))
    return "$month $day, $year"
}