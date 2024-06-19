package com.edominguez.compasstest.controller

import android.util.Log
import com.edominguez.compasstest.network.ApiClient
import com.edominguez.compasstest.network.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiController {

    private val apiService: ApiService by lazy {
        val client = ApiClient.getClient()
        requireNotNull(client) { "ApiClient returned null Retrofit instance" }
        client.create(ApiService::class.java)
    }

    fun fetchCharacterRequest(callback: (String?) -> Unit) {
        val call = apiService.fetchData()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body()
                    val html: String? = responseBody?.string()
                    callback(html)
                } else {
                    Log.e("NETWORK_ERROR", "Error fetching data: ")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("TAG", "Error fetching user data", t)
                callback(null)
            }
        })
    }

}