package com.simpledeeds.takehometestbareksa.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simpledeeds.takehometestbareksa.ui.DanaKelolaanFragment
import com.simpledeeds.takehometestbareksa.ui.ImbalHasilFragment

class MainViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ImbalHasilFragment()
            1 -> DanaKelolaanFragment()
            else -> Fragment()
        }
    }
}