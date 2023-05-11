package com.nytimes.poc.utils

import android.util.Log
import com.google.gson.Gson

object Logger {
    private var isDebugable = true
    fun e(tag: String, message: String) {
        if (isDebugable) {
            Log.e(tag, message + "")
        }
    }

    fun d(tag: String, message: String) {
        if (isDebugable) {
            Log.d(tag, message)
        }
    }

    fun dWithObject(tag: String, objects: Any) {
        if (isDebugable) {
            val gson = Gson()
            val jsonStr = gson.toJson(objects)
            Log.d(tag, jsonStr)

        }
    }

    fun i(tag: String, message: String) {
        if (isDebugable) {
            Log.i(tag, message)
        }
    }

}