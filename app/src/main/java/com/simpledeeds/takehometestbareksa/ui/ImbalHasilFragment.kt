package com.simpledeeds.takehometestbareksa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.tabs.TabLayoutMediator
import com.simpledeeds.takehometestbareksa.R
import com.simpledeeds.takehometestbareksa.adapters.SecondaryViewPagerAdapter
import com.simpledeeds.takehometestbareksa.data.remote.responses.DataX
import com.simpledeeds.takehometestbareksa.databinding.ImbalHasilFragmentBinding
import com.simpledeeds.takehometestbareksa.util.ClaimsXAxisValueFormatter
import com.simpledeeds.takehometestbareksa.util.Resource

class ImbalHasilFragment : Fragment(R.layout.imbal_hasil_fragment) {

    private var _binding: ImbalHasilFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ImbalHasilViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ImbalHasilFragmentBinding.bind(view)

        getChartData()
        setupRangeDayViewPager()
    }

    private fun getChartData() {
        viewModel.getProductChartData()
        viewModel.chartData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    binding.chartData.lineChart.isVisible = true
                    setChartValuesToView(response.data!!.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "something wrong happen", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.chartData.lineChart.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setChartValuesToView(data: DataX) {
        viewModel.setChartEntries(data)

        val vl = LineDataSet(viewModel.ciptaDanaEntries, "Cipta Dana Cash")
        val vl2 = LineDataSet(viewModel.bniEntries, "Bni")
        val vl3 = LineDataSet(viewModel.ascendReksaEntries, "Avrist Equity")

        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 2f
        vl.setCircleColor(R.color.ungu)
        vl.color = resources.getColor(R.color.ungu, resources.newTheme())

        vl2.setDrawValues(false)
        vl2.setDrawFilled(true)
        vl2.lineWidth = 2f
        vl2.setCircleColor(R.color.primary_green)
        vl2.color = resources.getColor(R.color.primary_green, resources.newTheme())

        vl3.setDrawValues(false)
        vl3.setDrawFilled(true)
        vl3.lineWidth = 2f
        vl3.setCircleColor(R.color.biru)
        vl3.color = resources.getColor(R.color.biru, resources.newTheme())

        binding.chartData.lineChart.xAxis.labelRotationAngle = 0f

        binding.chartData.lineChart.data = LineData(vl, vl2, vl3)
        binding.chartData.lineChart.invalidate()
        binding.chartData.lineChart.notifyDataSetChanged()

        binding.chartData.lineChart.xAxis.valueFormatter =
            ClaimsXAxisValueFormatter(viewModel.dates)

        binding.chartData.lineChart.setTouchEnabled(true)
        binding.chartData.lineChart.setPinchZoom(true)
        binding.chartData.lineChart.description.isEnabled = false
    }

    private fun setupRangeDayViewPager() {
        val adapter = SecondaryViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPagerProductData.adapter = adapter
        binding.tablayoutDateRange.getTabAt(2)?.select()
        TabLayoutMediator(
            binding.tablayoutDateRange,
            binding.viewPagerProductData
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "1W"
                1 -> tab.text = "1M"
                2 -> tab.text = "1Y"
                3 -> tab.text = "3Y"
                4 -> tab.text = "5Y"
                5 -> tab.text = "10Y"
                6 -> tab.text = "All"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}