package com.simpledeeds.takehometestbareksa.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simpledeeds.takehometestbareksa.ui.DetailProductFragment

class SecondaryViewPagerAdapter(
    childFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(childFragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> DetailProductFragment()
            1 -> DetailProductFragment()
            2 -> DetailProductFragment()
            3 -> DetailProductFragment()
            4 -> DetailProductFragment()
            5 -> DetailProductFragment()
            6 -> DetailProductFragment()
            else -> Fragment()
        }
    }
}
