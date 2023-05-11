package com.nytimes.poc.model.api


import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.model.schema.GeneralResponseModel
import com.nytimes.poc.utils.URLs
import retrofit2.Response
import retrofit2.http.*

interface NYApi {

    @GET(URLs.MOST_POPULAR_MOST_VIEWED)
    suspend fun mostViewed(@Query("api-key") apiKey:String): Response<GeneralResponseModel<ArrayList<Article>>>

}
