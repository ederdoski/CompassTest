package com.edominguez.compasstest.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("about")
    fun fetchData(): Call<String>
}

