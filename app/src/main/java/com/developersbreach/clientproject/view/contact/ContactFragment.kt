package com.developersbreach.clientproject.view.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentContactBinding
import com.developersbreach.clientproject.utils.RecyclerViewItemDecoration.Companion.setItemSpacing


class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding
    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        setItemSpacing(resources, binding.shopRecyclerView)
        binding.navController = findNavController()
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}