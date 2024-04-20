package com.example.utils.core

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.utils.core.Constant.BUNDLE_KEY
import com.google.gson.Gson


inline fun <reified T> T.toJsonString(): String {
    val gson = Gson()
    return gson.toJson(this)
}

inline fun <reified T> String.jsonParse(type: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, type)
}