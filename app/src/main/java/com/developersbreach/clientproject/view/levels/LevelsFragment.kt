package com.developersbreach.clientproject.view.levels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developersbreach.clientproject.databinding.FragmentLevelsBinding

class LevelsFragment : Fragment() {

    private lateinit var binding: FragmentLevelsBinding
    private lateinit var viewModel: LevelsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelsBinding.inflate(inflater, container, false)
        return binding.root
    }
}