package com.simpledeeds.takehometestbareksa.data.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginStatus(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)