package com.developersbreach.clientproject.view.badges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developersbreach.clientproject.databinding.FragmentBadgesBinding

class BadgesFragment : Fragment() {

    private lateinit var binding: FragmentBadgesBinding
    private lateinit var viewModel: BadgesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgesBinding.inflate(inflater, container, false)
        return binding.root
    }
}