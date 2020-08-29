package com.developersbreach.clientproject.view.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.databinding.FragmentDashboardBinding
import com.developersbreach.clientproject.model.Customers
import com.developersbreach.clientproject.model.UserAccount
import com.developersbreach.clientproject.utils.COLLECTION_PATH
import com.developersbreach.clientproject.utils.FIELD_MAIL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by viewModels<DashboardViewModel>()
    private var customer: Customers? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customer = DashboardFragmentArgs.fromBundle(requireArguments()).customerDashboardArgs
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                val googleUser = FirebaseAuth.getInstance().currentUser!!
                setGoogleUser(googleUser)
                setCurrentCustomer(googleUser)
            } else if (authState == AuthenticationState.UNAUTHENTICATED) {
                findNavController().navigate(
                    DashboardFragmentDirections.dashboardToLoginFragment()
                )
            }
        })
    }

    private fun setGoogleUser(currentUser: FirebaseUser) {
        val userAccount = UserAccount(
            currentUser.displayName!!,
            currentUser.email!!,
            currentUser.photoUrl.toString()
        )

        Glide.with(requireContext())
            .load(userAccount.profileUrl)
            .placeholder(R.drawable.ic_customer_icon)
            .circleCrop()
            .into(binding.dashboardUserProfile)
    }

    private fun setCurrentCustomer(googleUser: FirebaseUser) {
        showProgressBar()
        FirebaseFirestore.getInstance().collection(COLLECTION_PATH)
            .whereEqualTo(FIELD_MAIL, googleUser.email)
            .get()
            .addOnSuccessListener { querySnapshot ->
                firestoreSuccessListener(querySnapshot, googleUser)
            }
    }

    private fun firestoreSuccessListener(
        querySnapshot: QuerySnapshot,
        googleUser: FirebaseUser
    ) {
        if (!querySnapshot.isEmpty) {
            for (current in querySnapshot.documents) {
                customer = current.toObject(Customers::class.java)!!
                hideProgressBar()
                customerFound(customer!!, googleUser.photoUrl.toString())
            }
        } else if (customer != null) {
            hideProgressBar()
            customerFound(customer!!, googleUser.photoUrl.toString())
        } else {
            hideProgressBar()
            customerNotFound()
        }
    }

    private fun customerFound(customer: Customers, photoUrl: String) {
        bindCustomerViews(customer, requireContext(), photoUrl)
    }

    private fun bindCustomerViews(customer: Customers, context: Context, photoUrl: String) {
        binding.customerBillNumberTextView.text = customer.billNumber
        binding.customerDateTextView.text = customer.date
        binding.dashboardCustomerName.text = customer.name
        binding.dashboardCustomerMail.text = customer.email

        if (customer.status) {
            binding.statusIcon.setImageResource(R.drawable.ic_completed)
            binding.customerStatusText.text = context.getString(R.string.status_completed)
        } else {
            binding.statusIcon.setImageResource(R.drawable.ic_pending)
            binding.customerStatusText.text = context.getString(R.string.status_pending)
        }

        binding.dashboardFab.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.dashboardToDetailFragment(
                    customer, photoUrl
                )
            )
        }

        binding.customerFoundLayoutParent.visibility = View.VISIBLE
    }

    private fun customerNotFound() {
        binding.customerNotFoundLayoutParent.visibility = View.VISIBLE
        binding.dashboardFab.visibility = View.INVISIBLE

        binding.submitBillNumberDashboard.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.dashboardToBillNumberFragment()
            )
        }
    }

    private fun showProgressBar() {
        binding.progressBarDashboard.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarDashboard.visibility = View.GONE
    }
}