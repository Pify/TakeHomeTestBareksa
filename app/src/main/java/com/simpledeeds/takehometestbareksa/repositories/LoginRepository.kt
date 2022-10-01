package com.simpledeeds.takehometestbareksa.repositories

import com.simpledeeds.takehometestbareksa.data.remote.BareksaAPI
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val retrofitInstance: BareksaAPI
) {
    suspend fun loginUser() {

    }
}