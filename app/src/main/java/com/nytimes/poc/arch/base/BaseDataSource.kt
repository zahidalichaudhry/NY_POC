package com.nytimes.poc.arch.base

import com.nytimes.poc.model.interceptors.RequestResult
import com.nytimes.poc.model.schema.ErrorMessages
import com.nytimes.poc.model.schema.GeneralResponseModel
import com.nytimes.poc.utils.Logger
import org.json.JSONObject
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(
        call: suspend () -> Response<GeneralResponseModel<T>>,
        networkUtils: InternetConnection,
        errorMessages: ErrorMessages
    ): RequestResult<GeneralResponseModel<T>?> {
        if (networkUtils.isNetworkConnected()) {
            try {
                val response = call()
                if (response.isSuccessful && response.code() < 400) {
                    val body = response.body()
                    return RequestResult.success(body, response.body()?.message)
                }
                if (response.code() == 400) {
                    val errorBody = response.errorBody()?.charStream()?.readText()?.let {
                        JSONObject(it)
                    }
                    val message = errorBody?.getString("message") ?: errorMessages.badRequest
                    Logger.d("ErrorBody", message)
                    return RequestResult.error(message)
                }

                return try {
                    if (response.body() != null) {
                        val body = response.body()

                        RequestResult.error(body?.message ?: "")
                    } else {
                        val errorBody = response.errorBody()?.charStream()?.readText()?.let {
                            JSONObject(it)
                        }
                        RequestResult.error(
                            errorBody?.getString("message") ?: errorMessages.netWorkError
                        )
                    }
                } catch (e: java.lang.Exception) {
                    try {
                        val errorBody = response.errorBody()?.charStream()?.readText()?.let {
                            JSONObject(it)
                        }
                        RequestResult.error(
                            errorBody?.getString("message") ?: errorMessages.netWorkError
                        )
                    } catch (e: java.lang.Exception) {
                        RequestResult.error(errorMessages.netWorkError)
                    }
                }
            } catch (e: Exception) {
                return RequestResult.error(e.message ?: e.toString())
            }
        } else {
            return RequestResult.error(errorMessages.netWorkError)
        }
    }


}
