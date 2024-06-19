package com.edominguez.compasstest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory;

object ApiClient {

    private val baseUrl = "https://www.compass.com/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}