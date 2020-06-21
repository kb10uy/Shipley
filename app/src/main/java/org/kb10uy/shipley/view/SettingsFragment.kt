package org.kb10uy.shipley.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.kb10uy.shipley.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val showLicenses = findPreference<Preference>("action_show_licenses")
        val fragment = this
        showLicenses!!.setOnPreferenceClickListener {
            fragment.findNavController().navigate(R.id.action_settings_to_licenses)
            //val intent = Intent(fragment.context, OssLicensesMenuActivity::class.java)
            //intent.putExtra("title", getString(R.string.title_oss_licenses))
            //startActivity(intent)
            true
        }
    }
}
