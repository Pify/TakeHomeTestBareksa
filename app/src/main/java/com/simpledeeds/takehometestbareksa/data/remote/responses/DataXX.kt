package com.simpledeeds.takehometestbareksa.data.remote.responses


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("date")
    val date: String,
    @SerializedName("growth")
    val growth: Double,
    @SerializedName("value")
    val value: Double
)