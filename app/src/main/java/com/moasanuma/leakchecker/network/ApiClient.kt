package com.moasanuma.leakchecker.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("range/{pass}")
    suspend fun searchPass(@Path("pass") headPass: String): String
}