package com.developersbreach.clientproject.view.settings

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.developersbreach.clientproject.R
import com.firebase.ui.auth.AuthUI

class SettingsCompatFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(
            R.xml.preferences_customer, rootKey
        )

        val userAccountPreference: Preference = findPreference("UserAccountPreferenceKey")!!
        userAccountPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showUserAccountDialog()
            true
        }

        val logoutUserPreference: Preference = findPreference("UserLogoutPreferenceKey")!!
        logoutUserPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showLogoutUserDialog()
            true
        }
    }

    private fun showUserAccountDialog() {
        Toast.makeText(requireContext(), "User Account", Toast.LENGTH_SHORT).show()
    }

    private fun showLogoutUserDialog() {
        Toast.makeText(requireContext(), "Logout User", Toast.LENGTH_SHORT).show()
        AuthUI.getInstance().signOut(requireContext()).addOnCompleteListener {
            findNavController().navigate(R.id.settingsToMainFragment)
        }
    }
}