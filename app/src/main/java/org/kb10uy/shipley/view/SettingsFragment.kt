package org.kb10uy.shipley.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import org.kb10uy.shipley.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
