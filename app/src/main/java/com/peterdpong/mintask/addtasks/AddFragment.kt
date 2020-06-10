package com.peterdpong.mintask.addtasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

import com.peterdpong.mintask.R
import com.peterdpong.mintask.models.Task


class AddFragment : Fragment() {

    private lateinit var task: Task

    private lateinit var titleInput: com.google.android.material.textfield.TextInputEditText
    private lateinit var descInput: com.google.android.material.textfield.TextInputEditText
    private lateinit var dateTextView: TextView
    private lateinit var dateButton: Button
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private val addFragmentViewModel: AddFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(AddFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        task = Task()
        cancelButton = view.findViewById(R.id.cancelbtn)

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

    override fun onStart() {
        super.onStart()

        saveButton.setOnClickListener {
            addFragmentViewModel.addTask(task)
            parentFragmentManager.popBackStack()
        }

        cancelButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }

}
