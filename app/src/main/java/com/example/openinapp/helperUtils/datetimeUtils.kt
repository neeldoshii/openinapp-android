package com.example.openinapp.helperUtils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @return The current hour of the day as an integer.
 */
fun getCurrentTimeInHours(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR)
}

/**
 * Utility function to format a date string from format (`yyyy-MM-dd'T'HH:mm:ss.SSS'Z'`)
 * to  format (`dd MMM yyyy`).
 */
fun formatDateString(dateString: String): String {
    // Define the input and output date formats
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    // Parse the input date string to a Date object
    val date: Date? = inputFormat.parse(dateString)

    // Format the Date object to the desired output format
    return date?.let { outputFormat.format(it) } ?: ""
}
