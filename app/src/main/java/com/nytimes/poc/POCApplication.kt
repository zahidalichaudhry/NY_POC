package com.nytimes.poc

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.nytimes.poc.utils.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class POCApplication : Application() {


    private var lifecycleEventObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_STOP -> {
                Logger.d("Application", "Stoped")

                //your code here
            }

            Lifecycle.Event.ON_START -> {
                Logger.d("Application", "Started")

            }

            Lifecycle.Event.ON_PAUSE -> {
                Logger.d("Application", "Paused")

            }

            Lifecycle.Event.ON_RESUME -> {
                Logger.d("Application", "Resumed")


            }

            else -> {}
        }
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

}