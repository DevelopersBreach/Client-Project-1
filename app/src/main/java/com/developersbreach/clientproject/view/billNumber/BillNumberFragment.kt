package com.developersbreach.clientproject.view.billNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentBillNumberBinding
import com.developersbreach.clientproject.model.Customers
import com.developersbreach.clientproject.utils.COLLECTION_PATH
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class BillNumberFragment : Fragment() {

    private val viewModel by viewModels<BillNumberViewModel>()
    private lateinit var binding: FragmentBillNumberBinding
    private lateinit var billNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.clearIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.editText.doAfterTextChanged {
            billNumber =
                viewModel.validateBillNumber(it.toString(), binding.billNumberTextInput)
            performNullOrEmptyCheck()
        }

        binding.billNumberSubmit.setOnClickListener {
            if (billNumber.length == 4) {
                firestoreSearch(billNumber)
            }
        }
    }

    private fun firestoreSearch(billNumber: String) {
        val docSnapShot: Task<DocumentSnapshot> =
            FirebaseFirestore.getInstance().collection(COLLECTION_PATH).document(billNumber)
                .get()
        docSnapShot.addOnSuccessListener { document ->
            if (document.exists()) {
                val customer = document.toObject(Customers::class.java)
                findNavController().navigate(
                    BillNumberFragmentDirections.billNumberToDashboardFragment(customer)
                )
            } else {
                Toast.makeText(context, "Not Exists", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun performNullOrEmptyCheck() {
        if (binding.editText.text.isNullOrEmpty() || billNumber.length < 4) {
            binding.billNumberSubmit.visibility = View.INVISIBLE
        } else {
            binding.billNumberSubmit.visibility = View.VISIBLE
        }
    }
}