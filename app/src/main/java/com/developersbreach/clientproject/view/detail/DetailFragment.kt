package com.developersbreach.clientproject.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentDetailBinding
import com.developersbreach.clientproject.model.Account
import com.developersbreach.clientproject.model.Submission


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val submission: Submission? = DetailFragmentArgs.fromBundle(requireArguments()).submissionArgs
        val account: Account? = DetailFragmentArgs.fromBundle(requireArguments()).accountArgs
        val factory = DetailViewModelFactory(requireActivity().application, submission, account)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.submission = viewModel.submission
        binding.account = viewModel.account
        binding.navController = findNavController()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}