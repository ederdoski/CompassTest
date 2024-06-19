package com.edominguez.compasstest.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.edominguez.compasstest.R
import com.edominguez.compasstest.controller.ApiController
import com.edominguez.compasstest.model.WordCounter
import com.edominguez.compasstest.utils.Functions
import com.edominguez.compasstest.utils.HTMLParser
import com.edominguez.compasstest.utils.Preferences

class MainActivity : AppCompatActivity() {


    private lateinit var htmlParser: HTMLParser
    private lateinit var preferences: Preferences
    private lateinit var apiController: ApiController

    private lateinit var btnFetchData: Button
    private lateinit var btnCopyArray: Button
    private lateinit var tvCharacters: TextView
    private lateinit var lyWordsComponent: View
    private lateinit var tvWordsCounted: TextView
    private lateinit var lyCharacterComponent: View
    private lateinit var progressCircular: ProgressBar
    private lateinit var recyclerViewWords: RecyclerView
    private lateinit var tvQuantityOfCharacters: TextView
    private lateinit var tvQuantityOfCharactersResulted: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        initActivity()
        setOnClickListener()
    }

    // ---------- Logic Methods
    private fun initActivity() {
        htmlParser = HTMLParser()
        preferences = Preferences(this)
        apiController = ApiController(preferences)
    }

    private fun setOnClickListener() {
        btnFetchData.setOnClickListener {
            fetchCharacterRequest()
            fetchWordCounterRequest()
        }

        btnCopyArray.setOnClickListener {
            Functions.copyText(this, tvCharacters.text.toString())
        }
    }

    private fun fetchCharacterRequest() {
        showProgress()
        apiController.fetchCharacterRequestCoroutine { html ->
            html?.let {
                hideProgress()
                val characters = htmlParser.saveEveryTenCharacters(html)
                setCharactersUI(characters, html)
            } ?: run {
                hideProgress()
                Toast.makeText(this, getString(R.string.txt_error_data), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchWordCounterRequest() {
        showProgress()
        apiController.fetchWordCounterRequestCoroutine { html ->
            html?.let {
                hideProgress()
                val words = htmlParser.wordsCount(html)
                setCountWordsUI(words)
            } ?: run {
                hideProgress()
                Toast.makeText(this, getString(R.string.txt_error_data), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ---------- UI Methods

    private fun initUI() {
        progressCircular = findViewById(R.id.progressCircular)
        btnFetchData = findViewById(R.id.btnFetchData)
        btnCopyArray = findViewById(R.id.btnCopyArray)
        tvWordsCounted = findViewById(R.id.tvWordsCounted)
        tvCharacters = findViewById(R.id.txtArrayCharacters)
        lyWordsComponent = findViewById(R.id.lyWordsComponent)
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

    private fun setCountWordsUI(words: ArrayList<WordCounter>) {
        val tmpAdapter = WordCounterAdapter(this, words)

        lyWordsComponent.visibility = View.VISIBLE
        tvWordsCounted.text = getString(R.string.txt_word_quantity, words.size)

        recyclerViewWords = findViewById(R.id.recyclerViewWords)
        recyclerViewWords.adapter = tmpAdapter
    }

    private fun showProgress() {
        progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressCircular.visibility = View.GONE
    }
}