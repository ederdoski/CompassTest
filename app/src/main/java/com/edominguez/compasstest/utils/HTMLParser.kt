package com.edominguez.compasstest.utils

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

}