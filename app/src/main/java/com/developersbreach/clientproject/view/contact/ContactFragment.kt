package com.developersbreach.clientproject.view.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developersbreach.clientproject.databinding.FragmentContactBinding
import com.developersbreach.clientproject.model.Shop
import com.developersbreach.clientproject.utils.COLLECTION_PATH_PHOTOS
import com.developersbreach.clientproject.utils.DOCUMENT_IMAGES
import com.developersbreach.clientproject.utils.FIELD_IMAGES
import com.google.firebase.firestore.FirebaseFirestore


class ContactFragment : Fragment() {

    //private val viewModel by viewModels<ContactViewModel>()
    private lateinit var binding: FragmentContactBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collection = firestore.collection(COLLECTION_PATH_PHOTOS)
        val imagesDocument = collection.document(DOCUMENT_IMAGES).get()

        //val imagesDocument1 = imagesDocument.result.get()

        imagesDocument.addOnSuccessListener { docSnapshot ->

            val shop = docSnapshot.get(FIELD_IMAGES)!!

            val shopList: List<Shop> = listOf(Shop(shop.toString()))
            val adapter = ShopAdapter()
            adapter.submitList(shopList)
            binding.shopRecyclerView.adapter = adapter
        }
    }
}