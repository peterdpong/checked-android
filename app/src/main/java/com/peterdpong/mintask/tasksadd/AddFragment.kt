package com.peterdpong.mintask.tasksadd

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

import com.peterdpong.mintask.R


class AddFragment : Fragment() {

    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        cancelButton = view.findViewById(R.id.cancelbtn)
        cancelButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply{
            pathMotion = MaterialArcMotion()
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 425
        }
    }

}
