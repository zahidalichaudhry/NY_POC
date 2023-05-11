package com.nytimes.poc.arch.di

import com.nytimes.poc.arch.base.InternetConnection
import com.nytimes.poc.model.api.NYApi
import com.nytimes.poc.model.repository.NYRepo
import com.nytimes.poc.model.repository.NYRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideNYRepository(
    networkUtils: InternetConnection,
    api: NYApi,
  ): NYRepo {
    return NYRepoImp(api, networkUtils)
  }
}