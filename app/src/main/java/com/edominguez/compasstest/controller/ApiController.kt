package com.edominguez.compasstest.controller

import android.util.Log
import com.edominguez.compasstest.network.ApiClient
import com.edominguez.compasstest.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiController {

    private val apiService: ApiService by lazy {
        val client = ApiClient.getClient()
        requireNotNull(client) { "ApiClient returned null Retrofit instance" }
        client.create(ApiService::class.java)
    }

    fun fetchData() {
        val call: Call<String> = apiService.fetchData()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val data: String = response.body().toString()
                    Log.e("onResponse", data)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable?) {
                Log.e("onFailure", t.toString())

            }
        })
    }
}