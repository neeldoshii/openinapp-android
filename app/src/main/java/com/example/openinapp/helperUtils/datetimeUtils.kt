package com.example.openinapp.helperUtils

import java.util.Calendar

/**
 * @return The current hour of the day as an integer.
 */
fun getCurrentTimeInHours(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR)
}