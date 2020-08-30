package com.developersbreach.clientproject.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developersbreach.clientproject.model.Shop
import com.developersbreach.clientproject.utils.COLLECTION_PATH_PHOTOS
import com.developersbreach.clientproject.utils.DOCUMENT_IMAGES
import com.developersbreach.clientproject.utils.FIELD_ARRAY_IMAGES
import com.google.firebase.firestore.FirebaseFirestore

class ContactViewModel : ViewModel() {

    private val _imagesData = MutableLiveData<List<Shop>>()
    val imagesData: LiveData<List<Shop>>
        get() = _imagesData

    init {
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collection = firestore.collection(COLLECTION_PATH_PHOTOS)
        val document = collection.document(DOCUMENT_IMAGES).get()

        val shopList = ArrayList<Shop>()

        document.addOnSuccessListener { docSnapshot ->
            (docSnapshot.data?.get(FIELD_ARRAY_IMAGES) as ArrayList<*>).forEach {
                shopList.add(Shop(it.toString()))
            }

            _imagesData.value = shopList
        }
    }
}