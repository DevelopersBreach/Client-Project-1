package com.developersbreach.clientproject.view.achievements

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.developersbreach.clientproject.view.badges.BadgesFragment
import com.developersbreach.clientproject.view.levels.LevelsFragment

class AchievementsPagerAdapter(
    fragment: FragmentActivity,
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return LevelsFragment()
            1 -> return BadgesFragment()
        }
        return LevelsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}