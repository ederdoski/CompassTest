package com.edominguez.compasstest.utils

import com.edominguez.compasstest.model.WordCounter

class HTMLParser {

    fun saveEveryTenCharacters(html: String): String {
        val aLetters = arrayListOf <String>()
        var index = INITIAL_INDEX

        while (index < html.length) {
            aLetters.add(html[index].toString())
            index += NUMBER_TEN
        }

        return aLetters.toString()
    }

    fun wordsCount(html: String):ArrayList<WordCounter> {
        val lowercaseText = html.lowercase()

        val delimiters = charArrayOf(' ', '\n', '\t', '\r')

        val delimitedWords = lowercaseText.split(*delimiters)

        val aWordCounter = arrayListOf<WordCounter>()

        delimitedWords.forEach { word ->
            if (word.isNotBlank()) {
                val existingWordCounter = aWordCounter.find { it.word == word }

                if (existingWordCounter != null) {
                    val wordPosition = aWordCounter.indexOf(existingWordCounter)
                    aWordCounter[wordPosition].quantity = existingWordCounter.quantity + ONE
                } else {
                    aWordCounter.add(WordCounter(quantity = ONE, word = word))
                }
            }
        }
        aWordCounter.sortByDescending { it.quantity }
        return aWordCounter
    }
}