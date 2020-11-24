package com.developersbreach.clientproject.view.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.databinding.FragmentExploreBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A fragment that shows a list of items as a modal bottom sheet.
 */
class ExploreFragment : BottomSheetDialogFragment() {

    private val viewModel: ExploreViewModel by viewModels()
    private lateinit var binding: FragmentExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Shiva_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }
}