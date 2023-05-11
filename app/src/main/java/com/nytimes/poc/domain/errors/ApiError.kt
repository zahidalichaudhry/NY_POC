package com.nytimes.poc.domain.errors

class ApiError(override val message: String = "") : Exception(message)