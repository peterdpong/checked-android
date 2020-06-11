package com.peterdpong.mintask.listtasks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peterdpong.mintask.R
import com.peterdpong.mintask.settings.SettingsActivity

class NavigationBottomDialogFragment: BottomSheetDialogFragment() {

    private lateinit var settingsButton: TextView
    private lateinit var githubButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)

        settingsButton = view.findViewById(R.id.settings_button)
        githubButton = view.findViewById(R.id.github_button)

        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        githubButton.setOnClickListener{
            toURL("https://github.com/peterdpong/mintask-android/")
        }

        return view
    }

    private fun toURL(url: String){
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}