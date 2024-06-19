package com.edominguez.compasstest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edominguez.compasstest.R
import com.edominguez.compasstest.controller.ApiController

class MainActivity : AppCompatActivity() {

    private lateinit var apiController: ApiController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiController = ApiController()
        apiController.fetchData()
    }
}