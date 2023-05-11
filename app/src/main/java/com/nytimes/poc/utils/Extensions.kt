package com.nytimes.poc.utils

import android.content.Context
import com.nytimes.poc.R
import com.nytimes.poc.domain.errors.ApiError
import com.nytimes.poc.domain.errors.MissingInfoError
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Zahid Ali
 */
object Extensions {

  fun Exception.errorMessage(context: Context): String {
    return when (this) {
      is ApiError -> message.ifEmpty { context.getString(R.string.general_error) }
      is MissingInfoError -> context.getString(R.string.missing_info_error)
      else -> context.getString(R.string.general_error)
    }
  }
  fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    val naiveTrustManager = object : X509TrustManager {
      override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
      override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
      override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
      val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
      init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier { _, _ -> true }
    return this
  }

}