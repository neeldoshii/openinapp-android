package com.example.openinapp.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("favourite_links")
    val favouriteLinks: List<Any>,
    @SerializedName("overall_url_chart")
    val overallUrlChart: Any,
    @SerializedName("recent_links")
    val recentLinks: List<RecentLink>,
    @SerializedName("top_links")
    val topLinks: List<TopLink>
)