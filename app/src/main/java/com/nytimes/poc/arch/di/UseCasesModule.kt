package com.nytimes.poc.arch.di

import com.nytimes.poc.arch.base.BaseRepository
import com.nytimes.poc.domain.usecases.*
import com.nytimes.poc.model.repository.NYRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

  @Provides
  fun provideMostPopularMostViewedUserCase(repository: NYRepo): MostPopularMostViewedUserCase =
    MostPopularMostViewedUserCase(repository)

}