package com.simpledeeds.takehometestbareksa.data.remote.responses


import com.google.gson.annotations.SerializedName

data class NI002EQCDANSIE00(
    @SerializedName("data")
    val `data`: List<DataXX>,
    @SerializedName("error")
    val error: String
)