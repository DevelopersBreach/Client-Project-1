package com.developersbreach.clientproject.view.settings

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.utils.*
import com.developersbreach.clientproject.view.controller.settingsToIntro
import com.developersbreach.clientproject.view.controller.settingsToLogin
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar

class SettingsCompatFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(
            R.xml.preferences_customer, rootKey
        )

        val aboutAppPreference: Preference = findPreference(PREFERENCE_KEY_ABOUT_APP)!!
        aboutAppPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            settingsToIntro(findNavController())
            true
        }

        val logoutUserPreference: Preference = findPreference(PREFERENCE_KEY_USER_LOGOUT)!!
        logoutUserPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showLogoutUserDialog()
            true
        }
    }

    private fun showLogoutUserDialog() {
        //Toast.makeText(requireContext(), "Logout User", Toast.LENGTH_SHORT).show()
        Snackbar.make(requireView(), "Logged out", Snackbar.LENGTH_LONG).show()
        AuthUI.getInstance().signOut(requireContext()).addOnCompleteListener {
            settingsToLogin(findNavController())
        }
    }
}