package com.edominguez.compasstest.controller

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.edominguez.compasstest.network.ApiClient
import com.edominguez.compasstest.network.ApiService
import com.edominguez.compasstest.utils.EMPTY_STRING
import com.edominguez.compasstest.utils.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException
import java.net.HttpURLConnection

class ApiController(private val preferences: Preferences) {

    private val apiService: ApiService by lazy {
        val client = ApiClient.getClient()
        requireNotNull(client) { "ApiClient returned null Retrofit instance" }
        client.create(ApiService::class.java)
    }

    private suspend fun fetchCharacterRequest(): String? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.fetchData().awaitResponse()
                if (response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body()
                    responseBody?.string()
                } else {
                    Log.e("NETWORK_ERROR", "Error fetching data")
                    null
                }
            } catch (e: IOException) {
                Log.e("TAG", "Error fetching user data", e.cause)
                null
            }
        }
    }

    fun fetchDataUsingCoroutine(callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = fetchCharacterRequest()
            saveHTMLData(result)
            callback(result)
        }
    }

    fun saveHTMLData(htmlData:String?) {
        if(getHTMLData() != EMPTY_STRING && htmlData != null) {
            preferences.htmlData = htmlData
        }
    }

    fun getHTMLData():String {
        return preferences.htmlData
    }

}