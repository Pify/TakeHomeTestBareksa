package com.simpledeeds.takehometestbareksa.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.simpledeeds.takehometestbareksa.R
import com.simpledeeds.takehometestbareksa.data.remote.responses.Data
import com.simpledeeds.takehometestbareksa.databinding.DetailProductFragmentBinding
import com.simpledeeds.takehometestbareksa.util.Resource

class DetailProductFragment : Fragment(R.layout.detail_product_fragment) {

    private var _binding: DetailProductFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailProductViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailProductFragmentBinding.bind(view)

        getAndfetchData()
    }

    private fun getAndfetchData() {
        viewModel.getDetailProduct()
        viewModel.detailProduct.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    val responseData = response.data!!.data
                    fetchImage(responseData)
                    fetchName(responseData)
                    fetchReksaDanaType(responseData)
                    fetchImbalHasil(responseData)
                    fetchDanaKelolaan(responseData)
                    fetchMinBuy(responseData)
                    fetchTimeRange()
                    fetchRisk(responseData)
                    fetchInceptionDate(responseData)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "something wrong happen", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {}
            }
        })
    }

    private fun fetchInceptionDate(responseData: List<Data>) {
        binding.tvPeluncuranBni.text = viewModel.dateFormatter(responseData[0].details.inceptionDate)
        binding.tvPeluncuranCipta.text = viewModel.dateFormatter(responseData[1].details.inceptionDate)
        binding.tvPeluncuranAscend.text = viewModel.dateFormatter(responseData[2].details.inceptionDate)
    }

    private fun fetchRisk(data: List<Data>) {
        binding.tvRisikoBni.text = viewModel.checkRiskType(data[0].details.typeId)
        binding.tvRisikoCipta.text = viewModel.checkRiskType(data[1].details.typeId)
        binding.tvRisikoAscend.text = viewModel.checkRiskType(data[2].details.typeId)
    }

    private fun fetchTimeRange() {
        binding.tvJangkaBni.text = "5 tahun"
        binding.tvJangkaCipta.text = "1 tahun"
        binding.tvJangkaAscend.text = "5 tahun"
    }

    private fun fetchMinBuy(data: List<Data>) {
        binding.tvMinPembelianBni.text = viewModel.prettyCount(data[0].details.minSubscription)
        binding.tvMinPembelianCipta.text = viewModel.prettyCount(data[1].details.minSubscription)
        binding.tvMinPembelianAscend.text = viewModel.prettyCount(data[2].details.minSubscription)
    }

    private fun fetchDanaKelolaan(data: List<Data>) {
        binding.tvDanaKelolaBni.text = viewModel.prettyCount(data[0].details.totalUnit)
        binding.tvDanaKelolaCipta.text = viewModel.prettyCount(data[1].details.totalUnit)
        binding.tvDanaKelolaAscend.text = viewModel.prettyCount(data[2].details.totalUnit)
    }

    private fun fetchImbalHasil(data: List<Data>) {
        binding.tvImbalBni.text = "${data[0].details.returnOneYear}% / thn"
        binding.tvImbalCipta.text = "${data[1].details.returnOneYear}% / thn"
        binding.tvImbalAscend.text = "${data[2].details.returnOneYear}% / thn"
    }

    private fun fetchReksaDanaType(data: List<Data>) {
        binding.tvJenisDanaBni.text = data[0].details.type
        binding.tvJenisDanaCipta.text = data[1].details.type
        binding.tvJenisDanaAscend.text = data[2].details.type
    }

    private fun fetchName(data: List<Data>) {
        binding.tvNameBni.text = data[0].name
        binding.tvNameCiptaDana.text = data[1].name
        binding.tvNameAscend.text = data[2].name
    }

    private fun fetchImage(data: List<Data>) {
        binding.ivBni.load(data[0].details.imAvatar) {
            transformations(CircleCropTransformation())
        }
        binding.ivCiptaDana.load(data[1].details.imAvatar) {
            transformations(CircleCropTransformation())
        }
        binding.ivAscend.load(data[2].details.imAvatar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}