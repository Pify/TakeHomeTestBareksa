package com.simpledeeds.takehometestbareksa.repositories

import com.simpledeeds.takehometestbareksa.data.remote.BareksaAPI
import javax.inject.Inject

class BareksaRepository @Inject constructor(
    private val retrofitInstance: BareksaAPI
) {
    fun getProductCodes(): List<String> {
        return listOf("KI002MMCDANCAS00", "TP002EQCEQTCRS00", "NI002EQCDANSIE")
    }

    suspend fun getDetailProduct(productCodes: List<String>) =
        retrofitInstance.getProductDetail(productCodes)

    suspend fun getChartData(productCodes: List<String>) =
        retrofitInstance.getProductChart(productCodes)
}