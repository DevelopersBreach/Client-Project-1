package com.developersbreach.clientproject.view.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.developersbreach.clientproject.databinding.ItemIntroViewPagerBinding
import com.developersbreach.clientproject.model.Intro
import com.developersbreach.clientproject.view.intro.IntroViewPagerAdapter.*


class IntroViewPagerAdapter(
    private val introViewPager: ViewPager2
) : ListAdapter<Intro, IntroViewHolder>(IntroDiffCallback) {

    class IntroViewHolder(
        val binding: ItemIntroViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            intro: Intro,
            introViewPager: ViewPager2
        ) {
            binding.intro = intro
            binding.introViewPager2 = introViewPager
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            ItemIntroViewPagerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val intro = getItem(position)
        holder.bind(intro, introViewPager)
    }

    companion object IntroDiffCallback : DiffUtil.ItemCallback<Intro>() {
        override fun areItemsTheSame(oldItem: Intro, newItem: Intro): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Intro, newItem: Intro): Boolean {
            return oldItem.title == newItem.title
        }
    }
}