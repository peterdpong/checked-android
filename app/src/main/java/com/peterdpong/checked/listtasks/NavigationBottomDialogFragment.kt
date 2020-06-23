package com.peterdpong.checked.listtasks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peterdpong.checked.R
import com.peterdpong.checked.settings.SettingsActivity

class NavigationBottomDialogFragment: BottomSheetDialogFragment() {

    private lateinit var settingsButton: TextView
    private lateinit var privacyButton: TextView
    private lateinit var bugsButton: TextView
    private lateinit var translateButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)

        settingsButton = view.findViewById(R.id.settings_button)
        privacyButton = view.findViewById(R.id.privacy_button)
        bugsButton = view.findViewById(R.id.bugs_button)
        translateButton = view.findViewById(R.id.translate_button)


        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }

        privacyButton.setOnClickListener{
            toURL("https://peterdpong.github.io/checked/privacy.html")
        }

        bugsButton.setOnClickListener {
            val emails = arrayOf<String>("peterdpongdev@gmail.com")
            val subject = "Bug Report - Checked"
            composeEmail(emails, subject)

        }

        translateButton.setOnClickListener {
            toURL("https://github.com/peterdpong/checked-android/wiki/Translating")
        }

        return view
    }

    private fun toURL(url: String){
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun composeEmail(address: Array<String>, subject: String){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }

    }
}