package com.developersbreach.clientproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.ContentFirstFragmentBinding
import com.developersbreach.clientproject.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Access a Cloud Firestore instance from your Activity
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contentFirstFragment.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val included: ContentFirstFragmentBinding = binding.contentFirstFragment

        included.submitDetails.setOnClickListener {
            // Get a reference to the restaurants collection
            val customers: CollectionReference = firestore.collection("customers")

            val baseId: Int = included.baseIdEditText.text.toString().toInt()
            val billNumber: Int = included.customerBillNumberEditText.text.toString().toInt()
            val customerName = included.customerNameEditText.text.toString()
            val contact = included.customerContactEditText.text.toString()
            val currentDate = Calendar.getInstance().time.toString()
            val date = currentDate.removeRange(11, 30).drop(4)

            val customer = Customers(baseId, billNumber, customerName, contact, date)

            customers.add(customer)
                .addOnSuccessListener {
                    Snackbar.make(requireView(), "Added Customer", Snackbar.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Snackbar.make(requireView(), "UnSuccessful", Snackbar.LENGTH_SHORT).show()
                }
        }
    }
}