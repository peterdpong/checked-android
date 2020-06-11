package com.peterdpong.mintask.addtasks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.peterdpong.mintask.DatePickerFragment

import com.peterdpong.mintask.R
import com.peterdpong.mintask.models.Task
import java.util.*

private const val DIALOG_DATE = "DateDialog"
private const val RETURN_DATE = 0


class AddFragment : Fragment(), DatePickerFragment.Callbacks {

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
        saveButton = view.findViewById(R.id.savebtn)
        cancelButton = view.findViewById(R.id.cancelbtn)
        dateButton = view.findViewById(R.id.dateButton)
        dateTextView = view.findViewById(R.id.taskDate)
        titleInput = view.findViewById(R.id.titleTextInput)
        descInput = view.findViewById(R.id.descTextInput)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply{
            pathMotion = MaterialArcMotion()
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 375
        }
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Intentionally Left Blank
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Intentionally Left Blank
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.title = s.toString()
            }
        }

        val descWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Intentionally Left Blank
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Intentionally Left Blank
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.desc = s.toString()
            }
        }

        titleInput.addTextChangedListener(titleWatcher)
        descInput.addTextChangedListener(descWatcher)

        dateButton.setOnClickListener{
            DatePickerFragment.newInstance(task.dueDate).apply {
                setTargetFragment(this@AddFragment, RETURN_DATE)
                show(this@AddFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }

        saveButton.setOnClickListener {
            addFragmentViewModel.addTask(task)
            parentFragmentManager.popBackStack()
        }

        cancelButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDateSelected(date: Date) {
        task.dueDate = date
        updateUI()
    }

    private fun updateUI() {
        titleInput.setText(task.title)
        descInput.setText(task.desc)
        dateTextView.setText(task.dueDate.toString())
    }

}
