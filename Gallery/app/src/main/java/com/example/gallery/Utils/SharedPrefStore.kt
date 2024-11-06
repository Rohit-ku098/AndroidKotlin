package com.example.gallery.Utils

import android.content.Context
import android.content.SharedPreferences
import com.example.gallery.Model.MediaSection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun saveListToPreferences(context: Context, key: String, list: MutableList<MediaSection.Media>) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Convert the list to JSON format
    val gson = Gson()
    val json = gson.toJson(list)

    // Store the JSON string in SharedPreferences
    editor.putString(key, json)
    editor.apply()
}

fun getListFromPreferences(context: Context, key: String): MutableList<MediaSection.Media>? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val gson = Gson()

    // Get the JSON string from SharedPreferences
    val json = sharedPreferences.getString(key, null)

    // Convert JSON string back to MutableList<MyObject>
    val type = object : TypeToken<MutableList<MediaSection.Media>>() {}.type
    return gson.fromJson(json, type)
}
