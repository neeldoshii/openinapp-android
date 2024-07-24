package com.example.openinapp.model

data class Links(
    val icon: String ?= null,
    val linkName: String ? = "N/A",
    val creationDate: String ? = "N/A",
    val linkUrl : String ? = null,
    val totalClicks: Int ? = 0
)
