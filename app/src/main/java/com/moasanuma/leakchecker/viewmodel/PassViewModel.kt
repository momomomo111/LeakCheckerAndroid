package com.moasanuma.leakchecker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moasanuma.leakchecker.network.Api
import com.moasanuma.leakchecker.network.ApiService
import com.moasanuma.leakchecker.util.findMeLeak
import com.moasanuma.leakchecker.util.hashSHA1String
import kotlinx.coroutines.launch

class PassViewModel : ViewModel() {
    enum class PassApiStatus { LOADING, ERROR, DONE }

    private val _leakNum = MutableLiveData<Int>()
    val leakNum: LiveData<Int>
        get() = _leakNum

    private val _status = MutableLiveData<PassApiStatus>()
    val status: LiveData<PassApiStatus> = _status

    fun getLeakPassList(pass: String, service: ApiService = Api.retrofitService) {
        val hashPass = hashSHA1String(pass)
        val headPass = hashPass.take(5)
        viewModelScope.launch {
            try {
                val leakPass = service.getLeakPassProperties(headPass)
                val findLeakNum = findMeLeak(hashPass, leakPass)
                _leakNum.value = findLeakNum
                _status.value = PassApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PassApiStatus.ERROR
            }
        }
    }
}
