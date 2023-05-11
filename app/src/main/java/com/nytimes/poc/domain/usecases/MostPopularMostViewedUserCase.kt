package com.nytimes.poc.domain.usecases

import com.nytimes.poc.domain.errors.ApiError
import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.model.repository.NYRepo
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.model.schema.GeneralResponseModel


class MostPopularMostViewedUserCase constructor(private val repository: NYRepo) {

    suspend fun run(): GeneralResponseModel<ArrayList<Article>> {
        val response = repository.mostViewed()
        return if (response.status == RequestResult.Status.SUCCESS) {
            if (response.data?.data != null) {
                response.data
            } else throw ApiError(response.message ?: "")
        } else {
            throw ApiError(response.message ?: "")
        }
    }
}