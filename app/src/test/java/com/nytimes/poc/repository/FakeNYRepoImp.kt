package com.nytimes.poc.repository

import com.google.gson.Gson
import com.nytimes.poc.Constant
import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.model.repository.NYRepo
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.model.schema.GeneralResponseModel


class FakeNYRepoImp() : NYRepo {
  private var shouldReturnNetworkError = false
  fun setShouldReturnNetworkError(value: Boolean) {
    shouldReturnNetworkError = value
  }
  override suspend fun mostViewed(): RequestResult<GeneralResponseModel<ArrayList<Article>>?> {
    return if (shouldReturnNetworkError) {
      RequestResult.error("Network Error")
    } else  {
      val string = Constant.getStringData()
      val gson = Gson()
      val article:Article =  gson.fromJson(string, Article::class.java)
      val list = ArrayList<Article>()
      list.add(article)
      list.add(article)
      val generalResponseModel = GeneralResponseModel(
        list, "", "Success",
      )
      RequestResult.success(generalResponseModel)
    }  }
}