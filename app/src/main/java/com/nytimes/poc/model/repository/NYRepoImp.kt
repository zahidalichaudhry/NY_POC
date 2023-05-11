package com.nytimes.poc.model.repository

import com.nytimes.poc.BuildConfig
import com.nytimes.poc.arch.base.BaseRepository
import com.nytimes.poc.arch.base.InternetConnection
import com.nytimes.poc.model.api.NYApi
import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.model.schema.ErrorMessages
import com.nytimes.poc.model.schema.GeneralResponseModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NYRepoImp @Inject constructor(
    private val api: NYApi,
    private val networkUtils: InternetConnection,

) : BaseRepository(), NYRepo {
    override suspend fun mostViewed(): RequestResult<GeneralResponseModel<ArrayList<Article>>?> {
        val response = getResult({
            api.mostViewed(apiKey= BuildConfig.NY_API_KEY)
        }, networkUtils, errorMessages = ErrorMessages(netWorkError = "Network Error", badRequest = "Bad Request"))
        return response    }

}