package com.edominguez.compasstest

import com.edominguez.compasstest.model.WordCounter
import com.edominguez.compasstest.utils.HTMLParser
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HtmlParserTest {

    // Mocks
    private val parser = HTMLParser()

    @Test
    fun saveWordsEveryTenCharactersValidHTMLReturnsExpectedResult() {
        val html = "<html><body>Lorem ipsum dolor </body></html>"
        val expectedResult = "[d, p,  , h]"

        val result = parser.saveEveryTenCharacters(html)
        assertEquals(expectedResult, result)
    }

    @Test
    fun saveWordsEveryTenCharactersInValidHTMLReturnsExpectedResult() {
        val html = "<html><body>Lorem ipsum dolor </body></html>"
        val expectedResult = "[d, p, a, x, p]"

        val result = parser.saveEveryTenCharacters(html)
        assertNotEquals(expectedResult, result)
    }

    @Test
    fun wordsCountValidReturnsList() {
        val html = "<p> Compass Hello World </p> <p> Compass Hello World </p> <pp> Compass Hello World </p> <pp> Compass Hello World </p>"

        val expectedWordCount = arrayListOf(
            WordCounter( 4, "compass",),
            WordCounter(4, "hello", ),
            WordCounter( 4, "world"),
            WordCounter(4, "</p>"),
            WordCounter(2, "<p>"),
            WordCounter(2, "<pp>"),
        )

        val result = parser.wordsCount(html)

        assertEquals(expectedWordCount.size, result.size)

        for (i in expectedWordCount.indices) {
            assertEquals(expectedWordCount[i].word, result[i].word)
            assertEquals(expectedWordCount[i].quantity, result[i].quantity)
        }
    }
    @Test
    fun wordsCountInValidReturnsList() {
        val html = "<p> Compass Hello World </p> <p> Compass Hello World </p> <pp> Compass Hello World </p> <pp> Compass Hello World </pp>"

        // Resultado esperado incorrecto
        val expectedWordCount = arrayListOf(
            WordCounter(quantity = 3, word = "Compass"),
            WordCounter(quantity = 5, word = "helloo"),
            WordCounter(quantity = 1, word = "worldd"),
            WordCounter(quantity = 6, word = "</a>"),
            WordCounter(quantity = 1, word = "<x>"),
            WordCounter(quantity = 1, word = "<ppa>")
        )

        val result = parser.wordsCount(html)

        assertNotEquals(expectedWordCount.size, result.size)

        for (i in expectedWordCount.indices) {
            assertNotEquals(expectedWordCount[i].word, result[i].word)
            assertNotEquals(expectedWordCount[i].quantity, result[i].quantity)
        }
    }

}