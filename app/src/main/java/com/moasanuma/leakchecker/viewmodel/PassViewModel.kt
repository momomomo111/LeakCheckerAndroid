package com.moasanuma.leakchecker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moasanuma.leakchecker.network.Api
import com.moasanuma.leakchecker.util.hashSHA1String
import kotlinx.coroutines.launch

class PassViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getLeakPassList("")
    }

    fun getLeakPassList(pass: String) {
        val hashPass = hashSHA1String(pass)
        val headPass = hashPass.take(5)
        viewModelScope.launch {
            try {
                val result = Api.retrofitService.getLeakPassProperties(headPass)
                _response.value = result
                Log.d("test", "success$result")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.d("test", "error$e")
            }
        }
    }
}
