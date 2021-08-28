package com.simpledeeds.takehometestbareksa.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.simpledeeds.takehometestbareksa.R
import com.simpledeeds.takehometestbareksa.databinding.DanaKelolaanFragmentBinding

class DanaKelolaanFragment : Fragment(R.layout.dana_kelolaan_fragment) {

    private var _binding: DanaKelolaanFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DanaKelolaanViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DanaKelolaanFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}