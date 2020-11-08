package com.developersbreach.clientproject.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.activity = requireActivity()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                findNavController().popBackStack()
            }
        })

        setCallbacks()

        binding.signUpButton.setOnClickListener {
            binding.welcomeLayout.visibility = View.INVISIBLE
            binding.registerParent.visibility = View.VISIBLE
        }

        binding.submitButton.setOnClickListener {

            val text: String = binding.phoneNumberEditText.text.toString()
            val number: String = "+91".plus(text)

            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        setTextFields()
    }

    private fun setTextFields() {
        binding.usernameEditText.doAfterTextChanged {
            viewModel.isValidateUsername(it, binding.usernameInputLayout)
            performNullOrEmptyCheck()
        }

        binding.emailEditText.doAfterTextChanged {
            viewModel.isValidEmail(it, binding.emailInputLayout)
            performNullOrEmptyCheck()
        }

        binding.phoneNumberEditText.doAfterTextChanged {
            viewModel.isValidPhoneNumber(it, binding.phoneNumberInputLayout)
            performNullOrEmptyCheck()
        }
    }

    private fun performNullOrEmptyCheck() {
        if (binding.usernameEditText.text.isNullOrEmpty() ||
            binding.emailEditText.text.isNullOrEmpty() ||
            binding.phoneNumberEditText.text.isNullOrEmpty()
        ) {
            binding.submitButton.visibility = View.INVISIBLE
        } else {
            binding.submitButton.visibility = View.VISIBLE
        }
    }

    private fun setCallbacks() {

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Timber.e("onVerificationCompleted:$credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Timber.e("onVerificationFailed + $e")
                if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(requireContext(), "Invalid phone number.", Toast.LENGTH_SHORT)
                        .show()
                } else if (e is FirebaseTooManyRequestsException) {
                    Toast.makeText(requireContext(), "Quota exceeded.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Timber.d("LoginFragment - onCodeSent:$verificationId")
            }
        }
    }
}