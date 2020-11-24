package com.developersbreach.clientproject.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developersbreach.clientproject.databinding.FragmentLoginBinding
import com.developersbreach.clientproject.model.Account
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import timber.log.Timber
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCallbacks()
        setTextFields()

        binding.signUpButton.setOnClickListener {
            binding.welcomeLayout.visibility = View.INVISIBLE
            binding.registerParent.visibility = View.VISIBLE
        }

        binding.submitButton.setOnClickListener {

            val text: String = binding.phoneNumberEditText.text.toString()
            val number: String = "+91".plus(text)

            val options = PhoneAuthOptions.newBuilder(viewModel.firebaseAuth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

            binding.registerParent.visibility = View.INVISIBLE
            binding.verifyParent.visibility = View.VISIBLE

            binding.otpEditText.requestFocus()
            val imm: InputMethodManager? =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(binding.otpEditText, InputMethodManager.SHOW_IMPLICIT)
            binding.otpVerificationSentNumber.text = number
        }

        binding.submitOtpButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            val username = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val otpSubmitted = binding.otpEditText.text.toString()
            val account = Account(phoneNumber, username, email, "e")
            viewModel.verifyPhoneNumberWithCode(otpSubmitted, account)
        }
    }

    private fun validateSubmit() {
        viewModel.validateAllTextFields(
            binding.usernameEditText.text.toString(),
            binding.emailEditText.text.toString(),
            binding.phoneNumberEditText.text.toString()
        ).observe(viewLifecycleOwner, { fieldsAreValid ->
            if (fieldsAreValid) {
                binding.submitButton.visibility = View.VISIBLE
            } else {
                binding.submitButton.visibility = View.INVISIBLE
            }
        })
    }

    private fun setTextFields() {
        binding.usernameEditText.doAfterTextChanged {
            viewModel.isValidUsername(it.toString(), binding.usernameInputLayout)
            validateSubmit()
        }

        binding.emailEditText.doAfterTextChanged {
            viewModel.isValidEmail(it.toString(), binding.emailInputLayout)
            validateSubmit()
        }

        binding.phoneNumberEditText.doAfterTextChanged {
            viewModel.isValidPhoneNumber(it.toString(), binding.phoneNumberInputLayout)
            validateSubmit()
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

                // Save verification ID and resending token so we can use them later
                viewModel.storedVerificationId = verificationId
                resendToken = token
            }
        }
    }
}