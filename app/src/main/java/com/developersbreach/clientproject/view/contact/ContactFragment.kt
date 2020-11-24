package com.developersbreach.clientproject.view.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentContactBinding


class ContactFragment : Fragment() {

    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.includeContentContact.viewModel = viewModel
        binding.includeContentContact.fragment = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}