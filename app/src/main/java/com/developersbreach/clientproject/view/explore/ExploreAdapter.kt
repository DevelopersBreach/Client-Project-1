package com.developersbreach.clientproject.view.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemExploreBinding
import com.developersbreach.clientproject.model.Explore

class ExploreAdapter(
    private val exploreFragment: ExploreFragment
) : ListAdapter<Explore, ExploreAdapter.ExploreViewHolder>(Explore.DiffCallBack) {

    class ExploreViewHolder(
        private val binding: ItemExploreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            explore: Explore,
            exploreFragment: ExploreFragment
        ) {
            binding.explore = explore
            binding.fragment = exploreFragment
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(
            ItemExploreBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(getItem(position), exploreFragment)
    }
}