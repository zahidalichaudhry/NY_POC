package com.nytimes.poc.ui.article_detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nytimes.poc.arch.base.BaseViewModel
import com.nytimes.poc.domain.usecases.MostPopularMostViewedUserCase
import com.nytimes.poc.model.schema.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
) : BaseViewModel<ArticleDetailEvents>() {
  private val _viewState = MutableLiveData(ArticleDetailViewState.IDLE)

  val viewState: LiveData<ArticleDetailViewState> get() = _viewState

  fun setArticleDetailState(article: Article?){
    article?.let {
      _viewState.postValue(viewState.value?.copy(
        article = it
      ))
    }
  }

}