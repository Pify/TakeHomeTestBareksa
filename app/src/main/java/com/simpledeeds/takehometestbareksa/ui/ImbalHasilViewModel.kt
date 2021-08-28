package com.simpledeeds.takehometestbareksa.ui

import android.R.attr
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.simpledeeds.takehometestbareksa.data.remote.responses.Chart
import com.simpledeeds.takehometestbareksa.data.remote.responses.DataX
import com.simpledeeds.takehometestbareksa.repositories.BareksaRepository
import com.simpledeeds.takehometestbareksa.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import com.github.mikephil.charting.data.LineData

import com.github.mikephil.charting.data.LineDataSet

import android.R.attr.y

import android.R.attr.x




@HiltViewModel
class ImbalHasilViewModel @Inject constructor(
    private val repository: BareksaRepository
) : ViewModel() {

    private val _chartData = MutableLiveData<Resource<Chart>>()
    val chartData: LiveData<Resource<Chart>> = _chartData

    val bniEntries = ArrayList<Entry>()
    val ciptaDanaEntries = ArrayList<Entry>()
    val ascendReksaEntries = ArrayList<Entry>()

    val dates = ArrayList<String>()

    private val productCodes = repository.getProductCodes()

    fun getProductChartData() =
        viewModelScope.launch {
            _chartData.postValue(Resource.Loading())
            val response = repository.getChartData(productCodes)
            _chartData.postValue(handleChartDataResponse(response))
        }

    private fun handleChartDataResponse(response: Response<Chart>): Resource<Chart> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                setChartEntries(resultResponse.data)
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun setChartEntries(data: DataX) {
        for (position in data.kI002MMCDANCAS00.data.indices) {
            val chartData = data.kI002MMCDANCAS00.data
            val entries = Entry(chartData[position].growth.toFloat(), chartData[position].value.toFloat())
            ciptaDanaEntries.clear()
            ciptaDanaEntries.add(entries)

            dates.clear()
            dates.add(chartData[position].date)
        }

        for (i in data.nI002EQCDANSIE00.data.indices) {
            val chartData = data.nI002EQCDANSIE00.data
            val entries = Entry(chartData[i].growth.toFloat(), chartData[i].value.toFloat())
            bniEntries.clear()
            bniEntries.add(entries)
        }

        for (i in data.tP002EQCEQTCRS00.data.indices) {
            val chartData = data.tP002EQCEQTCRS00.data
            val entries = Entry(chartData[i].growth.toFloat(), chartData[i].value.toFloat())
            ascendReksaEntries.clear()
            ascendReksaEntries.add(entries)
        }
    }
}