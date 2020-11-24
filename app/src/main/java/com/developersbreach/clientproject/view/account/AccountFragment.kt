package com.developersbreach.clientproject.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentAccountBinding
import com.developersbreach.clientproject.model.Account

class AccountFragment : Fragment() {

    private lateinit var viewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account: Account? =
            AccountFragmentArgs.fromBundle(requireArguments()).account
        val factory: AccountViewModelFactory? = account?.let {
            AccountViewModelFactory(
                requireActivity().application,
                it
            )
        }
        viewModel = factory?.let {
            ViewModelProvider(this, it).get(AccountViewModel::class.java)
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}