package com.developersbreach.clientproject.view.billNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentBillNumberBinding


class BillNumberFragment : Fragment() {

    private val viewModel by viewModels<BillNumberViewModel>()
    private lateinit var binding: FragmentBillNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillNumberBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}