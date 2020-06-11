package com.peterdpong.checked.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.peterdpong.checked.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}