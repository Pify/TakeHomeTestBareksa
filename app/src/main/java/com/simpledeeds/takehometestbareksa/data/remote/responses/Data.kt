package com.simpledeeds.takehometestbareksa.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("code")
    val code: String,
    @SerializedName("details")
    val details: Details,
    @SerializedName("name")
    val name: String
)