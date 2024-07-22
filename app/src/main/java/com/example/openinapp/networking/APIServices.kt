package com.example.openinapp.networking

import com.example.openinapp.BuildConfig
import com.example.openinapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIServices {

    @GET("dashboardNew")
    @Headers(
        "Authorization: Bearer ${BuildConfig.BEARER_TOKEN}"
    )
    fun getDashboard() : Call<ApiResponse>
}