package com.developersbreach.clientproject.view.submissions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.clientproject.databinding.ItemSubmissionBinding
import com.developersbreach.clientproject.firestore.FirestoreAdapter
import com.developersbreach.clientproject.model.Submission
import com.developersbreach.clientproject.view.submissions.SubmissionAdapter.SubmissionViewHolder
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

class SubmissionAdapter(
    query: Query
) : FirestoreAdapter<SubmissionViewHolder>(query) {

    class SubmissionViewHolder(
        private val binding: ItemSubmissionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapShot: DocumentSnapshot?
        ) {
            val submission: Submission? = snapShot?.toObject(Submission::class.java)
            binding.submission = submission
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmissionViewHolder {
        return SubmissionViewHolder(
            ItemSubmissionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SubmissionViewHolder, position: Int) {
        getSnapshot(position).let { documentSnapshot ->
            holder.bind(documentSnapshot)
        }
    }
}