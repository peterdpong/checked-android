package com.peterdpong.mintask.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.peterdpong.mintask.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}