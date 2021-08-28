package com.simpledeeds.takehometestbareksa.ui

import android.icu.text.CompactDecimalFormat
import android.icu.util.CurrencyAmount
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simpledeeds.takehometestbareksa.data.remote.responses.DetailProduct
import com.simpledeeds.takehometestbareksa.repositories.BareksaRepository
import com.simpledeeds.takehometestbareksa.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val repository: BareksaRepository
) : ViewModel() {

    private val _detailProduct = MutableLiveData<Resource<DetailProduct>>()
    val detailProduct: LiveData<Resource<DetailProduct>> = _detailProduct

    val productCodes = repository.getProductCodes()

    fun getDetailProduct() =
        viewModelScope.launch {
            _detailProduct.postValue(Resource.Loading())
            val response = repository.getDetailProduct(productCodes)
            _detailProduct.postValue(handleDetailProductResponse(response))
        }

    private fun handleDetailProductResponse(response: Response<DetailProduct>): Resource<DetailProduct> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun checkRiskType(id: Int): String {
        return when(id) {
            1 -> "Rendah"
            2 -> "Sedang"
            3 -> "Tinggi"
            else -> "Resiko tidak diketahui"
        }
    }

    fun dateFormatter(date: String): String {
        val currentFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newDate = currentFormat.parse(date)

        val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return newFormat.format(newDate)
    }

    fun prettyCount(number: Number): String? {
        val suffix = arrayOf<String>(" ", "Ribu", "Juta", "Miliar", "Triliun")
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + " " + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }
}