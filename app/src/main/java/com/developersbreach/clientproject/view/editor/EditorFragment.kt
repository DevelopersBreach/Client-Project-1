package com.developersbreach.clientproject.view.editor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.databinding.FragmentEditorBinding
import com.developersbreach.clientproject.model.Submission
import com.google.android.material.transition.MaterialContainerTransform

class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    private lateinit var viewModel: EditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val submissionArgs: Submission? =
            EditorFragmentArgs.fromBundle(requireArguments()).submissionArgs
        val factory = EditorViewModelFactory(requireActivity().application, submissionArgs)
        viewModel = ViewModelProvider(this, factory).get(EditorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditorBinding.inflate(inflater, container, false)
        binding.navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.fab_new_submission)
            endView = binding.editorParent
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            containerColor = ContextCompat.getColor(requireContext(), R.color.colorSecondary)
            startContainerColor = ContextCompat.getColor(requireContext(), R.color.colorSecondary)
            endContainerColor = ContextCompat.getColor(requireContext(), R.color.colorSurface)
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.motion_duration_medium).toLong()
            addTarget(binding.editorParent)
        }
    }
}