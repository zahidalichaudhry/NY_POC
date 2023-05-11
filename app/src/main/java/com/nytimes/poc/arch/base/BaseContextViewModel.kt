package com.nytimes.poc.arch.base

import com.nytimes.poc.utils.universal.SingleLiveEvent
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseContextViewModel<T>(application: Application) : AndroidViewModel(application) {
    var viewModelContext: Application = application


    private val _dataEvent: SingleLiveEvent<BaseEvent<BaseDataEvents>> = SingleLiveEvent()
    val obDataEvent: LiveData<BaseEvent<BaseDataEvents>> = _dataEvent


    protected val events: SingleLiveEvent<BaseEvent<T>> = SingleLiveEvent()
    val obEvents: LiveData<BaseEvent<T>> = events


    val title = MutableLiveData<String>()


    protected fun showLoader(progressText: String = "") {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.ShowLoader(progressText)))
    }

    protected fun hideLoader() {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.HideLoader))
    }


    protected fun showError(error: String) {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.Error(error)))
    }

    protected fun showSuccessMessage(message: String) {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.Success(message)))
    }

    protected fun showToast(message: String) {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.Toast(message)))
    }

    fun back() {
        _dataEvent.postValue(BaseEvent(BaseDataEvents.Back))
    }

}
