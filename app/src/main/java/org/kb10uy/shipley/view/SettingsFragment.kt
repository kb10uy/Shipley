package org.kb10uy.shipley.view

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import org.kb10uy.shipley.R

/**
 * 設定画面
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val showLicenses = findPreference<Preference>("action_show_licenses")
        val fragment = this
        showLicenses!!.setOnPreferenceClickListener {
            fragment.findNavController().navigate(R.id.action_settings_to_licenses)
            true
        }
    }
}
