package com.peterdpong.mintask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.Hold
import com.peterdpong.mintask.addtasks.AddFragment


class ListFragment : Fragment() {

    private lateinit var fabButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        fabButton = view.findViewById(R.id.floating_action_button)
        fabButton.setOnClickListener {
            val fragment = AddFragment()
            parentFragmentManager
                .beginTransaction()
                .addSharedElement(it, "list_to_detail")
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

}
