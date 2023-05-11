package com.nytimes.poc.model.schema

data class ErrorMessages(
    var badRequest: String="Bad Request",
    var netWorkError: String="Network Error"
) {
}