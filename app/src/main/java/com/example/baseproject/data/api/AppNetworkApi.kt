package com.example.baseproject.data.api

import retrofit2.http.POST

interface AppNetworkApi {

    @POST(value = "markets")
    suspend fun getMarkets()
}


