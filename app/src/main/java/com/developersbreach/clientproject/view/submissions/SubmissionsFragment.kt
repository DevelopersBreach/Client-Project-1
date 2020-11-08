package com.developersbreach.clientproject.view.submissions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.auth.AuthenticationState

class SubmissionsFragment : Fragment() {

    private val viewModel: SubmissionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_submissions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                Toast.makeText(requireContext(), "Authenticated", Toast.LENGTH_SHORT).show()
            } else if (authState == AuthenticationState.UNAUTHENTICATED) {
                Toast.makeText(requireContext(), "UnAuthenticated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    SubmissionsFragmentDirections.submissionsToLoginFragment()
                )
            }
        })
    }

}