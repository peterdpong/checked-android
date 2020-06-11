package com.peterdpong.checked.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peterdpong.checked.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }

}