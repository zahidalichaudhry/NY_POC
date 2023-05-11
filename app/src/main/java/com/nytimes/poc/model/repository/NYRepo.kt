package com.nytimes.poc.model.repository

import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.model.schema.GeneralResponseModel

/**
 * Created by Zahid Ali
 */
interface NYRepo {
    suspend fun mostViewed(): RequestResult<GeneralResponseModel<ArrayList<Article>>?>
}
