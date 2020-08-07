package com.developersbreach.clientproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemListBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query


open class CustomersAdapter(
    query: Query
) :
    FirestoreAdapter<CustomersAdapter.CustomersViewHolder>(query) {

    class CustomersViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapShot: DocumentSnapshot
        ) {
            val customers: Customers = snapShot.toObject(Customers::class.java)!!
            binding.itemCustomerName.text = customers.name
            binding.itemBillNumber.text = customers.billNumber.toString()
            binding.itemCustomerContact.text = customers.contact
            binding.itemDate.text = customers.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        return CustomersViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.bind(getSnapshot(position)!!)
    }
}