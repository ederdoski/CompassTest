package com.edominguez.compasstest.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("about")
    fun fetchData(): Call<ResponseBody>
}

