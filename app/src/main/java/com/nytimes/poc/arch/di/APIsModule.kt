package com.nytimes.poc.arch.di

import com.nytimes.poc.model.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIsModule {
    @Provides
    fun provideAuthApi(@RetrofitNY retrofit: Retrofit): NYApi =
        retrofit.create(NYApi::class.java)

}














