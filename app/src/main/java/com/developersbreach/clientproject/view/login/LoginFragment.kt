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
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import timber.log.Timber
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                findNavController().popBackStack()
            }
        })

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

            verificationInProgress = true
            showProgress()

            binding.registerParent.visibility = View.INVISIBLE
            binding.verifyParent.visibility = View.VISIBLE

            binding.otpEditText.requestFocus()
            val imm: InputMethodManager? = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(binding.otpEditText, InputMethodManager.SHOW_IMPLICIT)
            binding.otpVerificationSentNumber.text = number

            hideProgress()
        }

        binding.submitOtpButton.setOnClickListener {
            verifyPhoneNumberWithCode(storedVerificationId, binding.otpEditText.text.toString())
        }
    }

    private fun showProgress() {
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBarLogin.visibility = View.INVISIBLE
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
                verificationInProgress = false
                hideProgress()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Timber.e("onVerificationFailed + $e")
                verificationInProgress = false
                hideProgress()
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
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        viewModel.firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Timber.e("signInWithCredential:success")
                val user = task.result?.user
                Timber.e("Credential: EMAIL ${user?.email}")
                Timber.e("Credential: NUMBER ${user?.phoneNumber}")
                Timber.e("Credential: NAME ${user?.displayName}")
            } else {
                // Sign in failed, display a message and update the UI
                Timber.e("signInWithCredential:failure ${task.exception}")
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Timber.e("Invalid OTP code.")
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(LoginViewModel.KEY_VERIFY_IN_PROGRESS, verificationInProgress)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            verificationInProgress = savedInstanceState.getBoolean(LoginViewModel.KEY_VERIFY_IN_PROGRESS)
        }
    }
}