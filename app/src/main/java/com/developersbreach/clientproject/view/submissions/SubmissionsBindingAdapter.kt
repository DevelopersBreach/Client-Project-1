package com.developersbreach.clientproject.view.submissions

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developersbreach.clientproject.model.Submission
import com.developersbreach.clientproject.view.explore.ExploreFragmentDirections
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter(
    "bindExpandExploreCardListener",
    "bindNewSubmissionFabListener",
    "bindSettingsImageListener"
)
fun MaterialCardView.setExpandExploreCardListener(
    navController: NavController,
    fabNewSubmission: FloatingActionButton,
    settingsImageView: ImageView
) {
    this.setOnClickListener {
        navController.navigate(ExploreFragmentDirections.toGlobalExplore())
    }

    fabNewSubmission.setOnClickListener {
        navController.navigate(SubmissionsFragmentDirections.submissionsToEditor(null))
    }

    settingsImageView.setOnClickListener {
        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
    }
}


@BindingAdapter(
    "bindItemSubmissionListener"
)
fun MaterialCardView.setExpandExploreCardListener(
    submission: Submission
) {
    this.setOnClickListener {
        findNavController().navigate(
            SubmissionsFragmentDirections.submissionToDetail(
                submission,
                null
            )
        )
    }
}