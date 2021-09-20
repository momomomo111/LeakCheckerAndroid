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

    private val _leakNum = MutableLiveData<Int>()
    val leakNum: LiveData<Int>
        get() = _leakNum

    init {
        getLeakPassList("")
    }

    fun getLeakPassList(pass: String) {
        val hashPass = hashSHA1String(pass)
        val headPass = hashPass.take(5)
        viewModelScope.launch {
            try {
                val leakPass = Api.retrofitService.getLeakPassProperties(headPass)
                val findLeakNum = findMeLeak(hashPass, leakPass)
                _leakNum.value = findLeakNum
            } catch (e: Exception) {
                _leakNum.value = -1
            }
        }
    }
}
