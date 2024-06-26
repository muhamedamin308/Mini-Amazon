package com.example.miniamazon.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.miniamazon.R
import com.example.miniamazon.ui.adapter.HomeViewPagerAdapter
import com.example.miniamazon.databinding.FragmentHomeBinding
import com.example.miniamazon.ui.fragments.home.category.AppliancesCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.ElectronicsCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.FashionCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.GroceryCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.MainCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.PerfumesCategoryFragment
import com.example.miniamazon.ui.fragments.home.category.VideoGamesCategoryFragment
import com.example.miniamazon.util.Constants
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categories = arrayListOf(
            MainCategoryFragment(),
            ElectronicsCategoryFragment(),
            AppliancesCategoryFragment(),
            FashionCategoryFragment(),
            GroceryCategoryFragment(),
            VideoGamesCategoryFragment(),
            PerfumesCategoryFragment()
        )

        binding.viewPagerHome.isUserInputEnabled = false

        val viewPagerAdapter = HomeViewPagerAdapter(
            categories,
            childFragmentManager,
            lifecycle
        )
        binding.viewPagerHome.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tableLayout, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = Constants.Taps.HOME
                1 -> tab.text = Constants.Taps.ELECTRONICS
                2 -> tab.text = Constants.Taps.APPLIANCES
                3 -> tab.text = Constants.Taps.FASHION
                4 -> tab.text = Constants.Taps.GROCERY
                5 -> tab.text = Constants.Taps.VIDEO_GAMES
                6 -> tab.text = Constants.Taps.PERFUMES
            }
        }.attach()
    }
}