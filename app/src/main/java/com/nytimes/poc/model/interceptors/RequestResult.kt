package com.nytimes.poc.model.interceptors


data class RequestResult<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T?, message: String? = ""): RequestResult<T?> {
            return RequestResult(Status.SUCCESS, data, message)
        }

        fun <T> error(
            message: String,
            data: T? = null
        ): RequestResult<T?> {
            return RequestResult(Status.ERROR, data, message)
        }
    }
}