package com.developersbreach.clientproject.view.settings

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.utils.*
import com.firebase.ui.auth.AuthUI

class SettingsCompatFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(
            R.xml.preferences_customer, rootKey
        )

        val userAccountPreference: Preference = findPreference(PREFERENCE_KEY_USER_ACCOUNT)!!
        userAccountPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showUserAccountDialog()
            true
        }

        val logoutUserPreference: Preference = findPreference(PREFERENCE_KEY_USER_LOGOUT)!!
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
            findNavController().navigate(
                SettingsFragmentDirections.settingsToLoginFragment()
            )
        }
    }
}