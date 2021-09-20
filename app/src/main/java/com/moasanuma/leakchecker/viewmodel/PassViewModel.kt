package com.moasanuma.leakchecker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moasanuma.leakchecker.network.Api
import com.moasanuma.leakchecker.util.findMeLeak
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
                val leakPass = Api.retrofitService.getLeakPassProperties(headPass)
                val findLeakNum = findMeLeak(hashPass, leakPass).toString()
                _response.value = findLeakNum
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}
