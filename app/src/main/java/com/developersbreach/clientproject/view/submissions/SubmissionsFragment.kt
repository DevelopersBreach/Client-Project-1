package com.developersbreach.clientproject.view.submissions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.databinding.FragmentSubmissionsBinding
import com.google.android.material.transition.MaterialElevationScale
import timber.log.Timber


class SubmissionsFragment : Fragment() {

    private lateinit var binding: FragmentSubmissionsBinding
    private val viewModel: SubmissionsViewModel by viewModels()
    private var adapter: SubmissionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubmissionsBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                showCustomerData()
            } else if (authState == AuthenticationState.UNAUTHENTICATED) {
                findNavController().navigate(
                    SubmissionsFragmentDirections.submissionsToLogin()
                )
            }
        })
    }

    private fun showCustomerData() {
        adapter = SubmissionAdapter(viewModel.billsQuery)
        Timber.e("Path == $viewModel.billsQuery")
        binding.submissionsRecyclerView.adapter = adapter
        // adapter?.stateRestorationPolicy = PREVENT_WHEN_EMPTY
        adapter?.startListening()
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}