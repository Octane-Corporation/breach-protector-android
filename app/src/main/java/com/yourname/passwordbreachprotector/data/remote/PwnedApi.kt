package com.octane.pbd.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PwnedApi {
    // Plaintext response; Retrofit Scalars converter returns it as String
    @GET("range/{prefix}")
    suspend fun getRange(
        @Path("prefix") prefix: String,
        @Header("Add-Padding") addPadding: String = "true" // privacy padding
    ): String
}
