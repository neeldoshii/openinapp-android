package com.example.openinapp.networking

import com.example.openinapp.helperUtils.BASEURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    fun getRetrofitInstance(): APIServices {

        //TODO("Make sure this log is always in DEBUG BUILD VARIANT")
        //Currently not added in DEBUG BUILD
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIServices::class.java)
    }
}