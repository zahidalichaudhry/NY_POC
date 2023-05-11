package com.nytimes.poc.model.schema

import com.google.gson.annotations.SerializedName

data class GeneralResponseModel<T>(
    @SerializedName("results")
    var data: T?,
    var requestLogId: String?,
    var message: String?,
    @SerializedName("status") var status: String? = "",
    @SerializedName("copyright") var copyright: String? = "",
) {

}