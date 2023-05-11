package com.nytimes.poc.arch.di

import com.nytimes.poc.model.interceptors.BaseHeadersInterceptor
import com.google.gson.GsonBuilder
import com.nytimes.poc.utils.URLs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesBaseHeadersInterceptor(): BaseHeadersInterceptor = BaseHeadersInterceptor()


    @RetrofitNY
    @Singleton
    @Provides
    fun providesMobileRetrofit(@OkHttpNY okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(URLs.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            ).build()


    @OkHttpNY
    @Singleton
    @Provides
    fun providesMobileHttpClient(
        baseHeaderInterceptor: BaseHeadersInterceptor,
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(baseHeaderInterceptor)
            .connectTimeout(45, TimeUnit.SECONDS).writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS).retryOnConnectionFailure(false).build()
    }
}