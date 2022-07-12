package com.geektech.newsapp

import android.content.SharedPreferences
import android.media.Image

class Prefs {
    private lateinit var preferences: SharedPreferences

    fun saveAvatar(image: String){
        preferences.edit().putString("image", image).apply()
    }

    fun getAvatar(): String? {
        return preferences.getString("image", null)
    }
}