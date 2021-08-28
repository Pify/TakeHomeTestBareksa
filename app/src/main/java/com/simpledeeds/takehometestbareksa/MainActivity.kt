package com.simpledeeds.takehometestbareksa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.simpledeeds.takehometestbareksa.adapters.MainViewPagerAdapter
import com.simpledeeds.takehometestbareksa.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setSupportActionBar(binding.toolbarPerbandingan)

        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Imbal Hasil"
                1 -> tab.text = "Dana Kelolaan"
            }
        }.attach()
    }
}