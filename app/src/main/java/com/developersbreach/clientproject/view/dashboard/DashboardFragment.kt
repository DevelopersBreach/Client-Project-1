package com.developersbreach.clientproject.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by viewModels<DashboardViewModel>()
//    private var account: Account? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val sharedPref = requireContext().getSharedPreferences(
//            getString(R.string.preference_intro_result_key), Context.MODE_PRIVATE
//        )
//
//        with(
//            sharedPref.getString(
//                getString(R.string.preference_intro_status_key),
//                getString(R.string.preference_intro_fragment_not_shown_value)
//            )
//        ) {
//            if (!this.equals(getString(R.string.preference_intro_fragment_shown_value))) {
//                // dashboardToIntro(findNavController())
//            }
//        }
//
//        account = DashboardFragmentArgs.fromBundle(requireArguments()).dashboardArgs
//    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
//            if (authState == AuthenticationState.AUTHENTICATED) {
//                val googleUser = FirebaseAuth.getInstance().currentUser!!
//                setGoogleUser(googleUser)
//                setCurrentCustomer(googleUser)
//            } else if (authState == AuthenticationState.UNAUTHENTICATED) {
//                dashboardToLogin(findNavController())
//            }
//        })

        if (viewModel.isConnected) {
            binding.includeNoInternetLayout.boxViewNoInternet.visibility = View.INVISIBLE
            binding.boxView.visibility = View.VISIBLE
            binding.dashboardUserProfile.visibility = View.VISIBLE
        } else {
            binding.includeNoInternetLayout.boxViewNoInternet.visibility = View.VISIBLE
            binding.boxView.visibility = View.INVISIBLE
            binding.dashboardUserProfile.visibility = View.INVISIBLE
        }
    }

//    private fun setGoogleUser(currentUser: FirebaseUser) {
//        val userAccount = Account(
//            currentUser.phoneNumber,
//            currentUser.displayName!!,
//            currentUser.email!!,
//            currentUser.photoUrl.toString()
//        )
//
//        Glide.with(requireContext())
//            .load(userAccount.photoUrl)
//            .placeholder(R.drawable.ic_customer_icon)
//            .circleCrop()
//            .into(binding.dashboardUserProfile)
//    }

//    private fun setCurrentCustomer(googleUser: FirebaseUser) {
//        showProgressBar()
//        FirebaseFirestore.getInstance().collection(COLLECTION_PATH)
//            .whereEqualTo(FIELD_MAIL, googleUser.email)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                firestoreSuccessListener(querySnapshot, googleUser)
//            }
//    }

//    private fun firestoreSuccessListener(
//        querySnapshot: QuerySnapshot,
//        googleUser: FirebaseUser
//    ) {
//        if (!querySnapshot.isEmpty) {
//            for (current in querySnapshot.documents) {
//                account = current.toObject(Account::class.java)!!
//                hideProgressBar()
//                customerFound(account!!, googleUser.photoUrl.toString())
//            }
//        } else if (account != null) {
//            hideProgressBar()
//            customerFound(account!!, googleUser.photoUrl.toString())
//        } else {
//            hideProgressBar()
//            customerNotFound()
//        }
//    }

//    private fun customerFound(account: Account, photoUrl: String) {
//        bindCustomerViews(customer, requireContext(), photoUrl)
//    }

//    private fun bindCustomerViews(account: Account, context: Context, photoUrl: String) {
//        binding.customerBillNumberTextView.text = customer.billNumber
//        binding.customerDateTextView.text = customer.date
//        binding.dashboardCustomerName.text = customer.name
//        binding.dashboardCustomerMail.text = customer.email
//
//        if (customer.status == DELIVERY_STATUS_COMPLETED) {
//            binding.statusIcon.setImageResource(R.drawable.ic_completed)
//            binding.customerStatusText.text = context.getString(R.string.status_completed)
//        } else if (customer.status == DELIVERY_STATUS_PENDING) {
//            binding.statusIcon.setImageResource(R.drawable.ic_pending)
//            binding.customerStatusText.text = context.getString(R.string.status_pending)
//        }
//
//        binding.dashboardFab.setOnClickListener {
//            dashboardToDetail(customer, photoUrl, findNavController())
//        }
//
//        binding.customerFoundLayoutParent.visibility = View.VISIBLE
//    }

//    private fun customerNotFound() {
//        binding.customerNotFoundLayoutParent.visibility = View.VISIBLE
//        binding.dashboardFab.visibility = View.INVISIBLE
//
//        binding.submitBillNumberDashboard.setOnClickListener {
//            dashboardToBillNumber(findNavController())
//        }
//    }

//    private fun showProgressBar() {
//        binding.progressBarDashboard.visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBar() {
//        binding.progressBarDashboard.visibility = View.GONE
//    }
}