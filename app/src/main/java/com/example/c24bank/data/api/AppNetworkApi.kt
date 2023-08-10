package com.example.c24bank.data.api

import com.example.c24bank.data.api.model.ProductsNetworkResponse
import retrofit2.http.GET

interface AppNetworkApi {

    @GET(value = "products-test.json")
    suspend fun getProducts() : ProductsNetworkResponse
}


