package com.nytimes.poc.ui.mostpopular

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nytimes.poc.arch.base.BaseViewModel
import com.nytimes.poc.domain.usecases.MostPopularMostViewedUserCase
import com.nytimes.poc.utils.Extensions.errorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularListViewModel @Inject constructor(
  private val mostPopularMostViewedUserCase: MostPopularMostViewedUserCase,
) : BaseViewModel<MostPopularListEvents>() {
  private val _viewState = MutableLiveData(MostPopularListState.IDLE)

  val viewState: LiveData<MostPopularListState> get() = _viewState


  init {
    loadMostPopularList()
  }

   fun loadMostPopularList() = CoroutineScope(Dispatchers.IO).launch {
    try {
      showLoader("")
      val response = mostPopularMostViewedUserCase.run(
      )
      response.data?.let {
        _viewState.postValue(viewState.value?.copy(
          articlesListState = it
        ))       }
      showSuccessMessage(response.message?:"Success")
    } catch (ex: Exception) {
      ex.printStackTrace()
      showError(ex.message.toString())
    }
  }

}