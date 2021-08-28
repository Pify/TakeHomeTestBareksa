package com.simpledeeds.takehometestbareksa.data.remote.responses


import com.google.gson.annotations.SerializedName

data class DetailProduct(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("total_data")
    val totalData: Int
)