@file:Suppress("unused")

package com.developersbreach.clientproject.view.achievements

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.databinding.FragmentAchievementsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AchievementsFragment : Fragment() {

    private lateinit var binding: FragmentAchievementsBinding
    private val viewModel: AchievementsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide(Gravity.START).apply { duration = 250.toLong() }
        returnTransition = Slide(Gravity.START).apply { duration = 250.toLong() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = AchievementsPagerAdapter(requireActivity())
        binding.achievementsViewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(
            binding.mainTabs,
            binding.achievementsViewPager,
            configureTabs()
        ).attach()
    }

    private fun configureTabs(): (tab: TabLayout.Tab, position: Int) -> Unit = { tab, position ->
        val tabNames = listOf(
            requireContext().getString(R.string.tab_name_levels),
            requireContext().getString(R.string.tab_name_badges)
        )
        tab.text = tabNames[position]
    }
}