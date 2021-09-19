package com.moasanuma.leakchecker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moasanuma.leakchecker.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassViewModel @Inject constructor(
    private val apiClient: ApiClient
) : ViewModel() {

    enum class PassApiStatus { LOADING, ERROR, DONE }

    private val _properties = MutableLiveData<String>()
    val properties: LiveData<String> = _properties

    private val _status = MutableLiveData<PassApiStatus>()
    val status: LiveData<PassApiStatus> = _status

    init {
        getPassProperties("")
    }

    fun getPassProperties(pass: String) {
        viewModelScope.launch {
            _status.value = PassApiStatus.LOADING
            try {
                _properties.value = apiClient.searchPass().also {
                    Log.d("test", "prop${it}")
                }
                _status.value = PassApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PassApiStatus.ERROR
            }
        }
    }
}
