package com.moasanuma.leakchecker.viewmodel

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
        getPassProperties()
    }

    private fun getPassProperties() {
        viewModelScope.launch {
            _status.value = PassApiStatus.LOADING
            try {
                _properties.value = apiClient.searchPass("1234")
                _status.value = PassApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PassApiStatus.ERROR
            }
        }
    }
}
