package com.nytimes.poc.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.nytimes.poc.MainCoroutineRule
import com.nytimes.poc.domain.usecases.MostPopularMostViewedUserCase
import com.nytimes.poc.getOrAwaitValueTest
import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.repository.FakeNYRepoImp
import com.nytimes.poc.ui.mostpopular.MostPopularListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostListViewModelTest {

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  private lateinit var viewModel: MostPopularListViewModel
  private lateinit var repository: FakeNYRepoImp
  private lateinit var userCase: MostPopularMostViewedUserCase

  @Before
  fun setup() {
    repository = FakeNYRepoImp()
    userCase = MostPopularMostViewedUserCase(repository)
    viewModel = MostPopularListViewModel(userCase)
  }
  @Test
  fun articleListingFailCase() {
    repository.setShouldReturnNetworkError(true)
    viewModel.loadMostPopularList()
    val result = viewModel.viewState.getOrAwaitValueTest().articlesListState
    assertThat(result.size).isEqualTo(0)
  }
  @Test
  fun articleListingSuccessCase() {
    repository.setShouldReturnNetworkError(false)
    viewModel.loadMostPopularList()
    val result = viewModel.viewState.getOrAwaitValueTest().articlesListState
    assertThat(result.size).isEqualTo(2)
  }
}