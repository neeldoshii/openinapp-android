package com.example.openinapp.model

data class Analytics(
    val analyticsIcon: Int? = null,
    val analyticsCount: Any? = null, // Used Any because Location & Sources uses String & Click Int
    val analyticsDescription: String
)
