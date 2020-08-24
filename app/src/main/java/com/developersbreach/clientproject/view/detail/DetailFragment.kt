package com.developersbreach.clientproject.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentDetailBinding
import com.developersbreach.clientproject.model.Customers
import com.developersbreach.clientproject.viewModel.DetailViewModel
import com.developersbreach.clientproject.viewModel.DetailViewModelFactory


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customers: Customers = DetailFragmentArgs.fromBundle(requireArguments()).customerDetailArgs
        val photoUrl = DetailFragmentArgs.fromBundle(requireArguments()).photoUrlArgs
        val factory = DetailViewModelFactory(requireActivity().application, customers, photoUrl)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        binding.customer = viewModel.selectedCustomer
        binding.photoUrlString = viewModel.selectedUser
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetailFragment.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}