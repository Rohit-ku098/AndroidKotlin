package com.example.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel(){
    var quotes : Array<Quote> = emptyArray<Quote>()
    var index = 0
    init {
        quotes = getQuotesFromJson()
    }

    fun getQuote() = quotes[index]

    fun nextQuote() {
        index = (index + 1) % quotes.size
    }

    fun previousQuote() {
        index = (index - 1 + quotes.size) % quotes.size
    }

    private fun getQuotesFromJson():  Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()

        return Gson().fromJson(jsonString, Array<Quote>::class.java)
    }
}