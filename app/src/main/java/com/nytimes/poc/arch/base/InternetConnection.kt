package com.nytimes.poc.arch.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InternetConnection @Inject constructor(
    private var connectivityManager: ConnectivityManager,
    @ApplicationContext var context: Context
) {
    fun isNetworkConnected(): Boolean {

        val n = connectivityManager.activeNetwork

        if (n != null) {
            val nc = connectivityManager.getNetworkCapabilities(n)

            if (nc != null) {
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            }
        }

        return false
    }
}