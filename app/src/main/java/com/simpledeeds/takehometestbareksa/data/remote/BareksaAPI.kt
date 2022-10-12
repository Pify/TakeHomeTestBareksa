package com.simpledeeds.takehometestbareksa.data.remote

import com.simpledeeds.takehometestbareksa.data.remote.responses.Chart
import com.simpledeeds.takehometestbareksa.data.remote.responses.DetailProduct
import com.simpledeeds.takehometestbareksa.data.remote.responses.LoginStatus
import retrofit2.Response
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("/login")
    suspend fun loginUser(
        @Field("username")
        username: String,
        @Field("password")
        password: String
    ) : Response<LoginStatus>

    @FormUrlEncoded
    @POST("/signout")
    suspend fun signOut(
        @Field("token")
        token: String,
    ) : Response<LoginStatus>
}