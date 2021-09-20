package com.moasanuma.leakchecker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moasanuma.leakchecker.network.Api
import kotlinx.coroutines.launch

class PassViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getLeakPassList()
    }

    fun getLeakPassList() {
        viewModelScope.launch {
            try {
                val result = Api.retrofitService.getLeakPassProperties()
                _response.value = "Success: $result Mars properties retrieved"
                Log.d("test", "success$result")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.d("test", "error$e")
            }
        }
    }
}
