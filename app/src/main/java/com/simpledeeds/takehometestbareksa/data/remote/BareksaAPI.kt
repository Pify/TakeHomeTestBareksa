package com.simpledeeds.takehometestbareksa.data.remote

import com.simpledeeds.takehometestbareksa.data.remote.responses.Chart
import com.simpledeeds.takehometestbareksa.data.remote.responses.DetailProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BareksaAPI {

    @GET("/takehometest/apps/compare/detail")
    suspend fun getProductDetail(
        @Query("productCodes[]")
        productCodes: List<String>
    ): Response<DetailProduct>

    @GET("/takehometest/apps/compare/chart")
    suspend fun getProductChart(
        @Query("productCodes[]")
        productCodes: List<String>
    ): Response<Chart>
}