package com.edominguez.compasstest.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edominguez.compasstest.R
import com.edominguez.compasstest.controller.ApiController
import com.edominguez.compasstest.utils.Functions
import com.edominguez.compasstest.utils.HTMLParser
import com.edominguez.compasstest.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var btnFetchData: Button
    private lateinit var btnCopyArray: Button
    private lateinit var tvCharacters: TextView
    private lateinit var lyCharacterComponent: View
    private lateinit var tvQuantityOfCharacters: TextView
    private lateinit var tvQuantityOfCharactersResulted: TextView

    private lateinit var htmlParser: HTMLParser
    private lateinit var preferences: Preferences
    private lateinit var apiController: ApiController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        initActivity()
        setOnClickListener()
    }

    // Logic Methods
    private fun initActivity() {
        htmlParser = HTMLParser()
        preferences = Preferences(this)
        apiController = ApiController(preferences)
    }

    private fun setOnClickListener() {
        btnFetchData.setOnClickListener {
            fetchCharacterRequest()
        }

        btnCopyArray.setOnClickListener {
            Functions.copyText(this, tvCharacters.text.toString())
        }
    }

    private fun fetchCharacterRequest() {
        apiController.fetchDataUsingCoroutine { html ->
            html?.let {
                val characters = htmlParser.saveEveryTenCharacters(html)
                setCharactersUI(characters, html)
            } ?: run {
                Toast.makeText(this, getString(R.string.txt_error_data), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // UI Methods

    private fun initUI() {
        btnFetchData = findViewById(R.id.btnFetchData)
        btnCopyArray = findViewById(R.id.btnCopyArray)
        tvCharacters = findViewById(R.id.txtArrayCharacters)
        lyCharacterComponent = findViewById(R.id.lyCharacterComponent)
        tvQuantityOfCharacters = findViewById(R.id.tvQuantityOfCharacters)
        tvQuantityOfCharactersResulted = findViewById(R.id.tvQuantityOfCharactersResulted)
    }

    private fun setCharactersUI(characters:String, html:String) {
        lyCharacterComponent.visibility = View.VISIBLE
        tvCharacters.text = characters
        tvQuantityOfCharacters.text = html.length.toString()
        tvQuantityOfCharactersResulted.text = characters.length.toString()
    }
}