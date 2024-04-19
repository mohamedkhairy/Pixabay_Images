package com.example.utils.core

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.utils.core.Constant.BUNDLE_KEY
import com.example.utils.model.Hit
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

inline fun <reified T> NavController.navWithData (key: String, value: T){
    this.currentBackStackEntry?.arguments?.apply {
        putBundle(BUNDLE_KEY, bundleOf(key to value))
//        set(key, value)
    }
}

inline fun <reified T> NavController.getNavData(key: String): T? =
        this.previousBackStackEntry?.savedStateHandle?.get<T>(key)


//inline  fun <reified T> T.toJsonString(): String {
//    val gson = Gson()
//    var myObjectString = gson.toJson(myObject, MyObject::class.java)
//}