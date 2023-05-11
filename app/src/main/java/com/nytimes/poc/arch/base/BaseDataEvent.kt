package com.nytimes.poc.arch.base

sealed class BaseDataEvents {
    class ShowLoader(val progressText: String = "") : BaseDataEvents()
    object HideLoader : BaseDataEvents()
    class Exception(val message: String) : BaseDataEvents()
    class Error(val message: String, val title: String = "Sorry!") : BaseDataEvents()
    class Toast(val message: String) : BaseDataEvents()
    class Success(val message: String) : BaseDataEvents()
    object ForceLogout : BaseDataEvents()
    object Back : BaseDataEvents()


}