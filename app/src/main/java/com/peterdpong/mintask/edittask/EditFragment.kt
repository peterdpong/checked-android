package com.peterdpong.mintask.edittask

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
import androidx.lifecycle.Observer
import com.peterdpong.mintask.DatePickerFragment
import android.text.format.DateFormat
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

import com.peterdpong.mintask.R
import com.peterdpong.mintask.listtasks.DATE_FORMAT
import com.peterdpong.mintask.models.Task
import java.util.*

private const val ARG_TASK_ID = "task_id"
private const val DIALOG_DATE = "DateDialog"
private const val RETURN_DATE = 0
private const val NO_TITLE_TEXT = "Add a task title to save."

class EditFragment : Fragment(), DatePickerFragment.Callbacks {

    private lateinit var task: Task

    private lateinit var titleInput: com.google.android.material.textfield.TextInputEditText
    private lateinit var descInput: com.google.android.material.textfield.TextInputEditText
    private lateinit var dateTextView: TextView
    private lateinit var dateButton: Button
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var editTaskLayout: ConstraintLayout


    private val editFragmentViewModel: EditFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(EditFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        titleInput = view.findViewById(R.id.titleTextInput)
        descInput = view.findViewById(R.id.descTextInput)
        dateTextView = view.findViewById(R.id.taskDate)
        dateButton = view.findViewById(R.id.dateButton)
        saveButton = view.findViewById(R.id.savebtn)
        cancelButton = view.findViewById(R.id.cancelbtn)
        editTaskLayout = view.findViewById(R.id.edittasklayout)

        task = Task()
        val taskId: UUID = arguments?.getSerializable(ARG_TASK_ID) as UUID
        editFragmentViewModel.loadTask(taskId)
        editTaskLayout.transitionName = taskId.toString()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply{
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 375
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editFragmentViewModel.taskLiveData.observe(viewLifecycleOwner, Observer{ task ->
            task?.let{
                this.task = task
                updateUI()
            }
        })
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
                setTargetFragment(this@EditFragment, RETURN_DATE)
                show(this@EditFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }

        saveButton.setOnClickListener {
            if(task.title.isEmpty()){
                Snackbar.make(editTaskLayout, NO_TITLE_TEXT, Snackbar.LENGTH_SHORT)
                    .show()
            }

            editFragmentViewModel.saveTask(task)
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
        dateTextView.setText(DateFormat.format(DATE_FORMAT, task.dueDate))
    }

    companion object{
        fun newInstance(taskId: UUID): EditFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TASK_ID, taskId)
            }

            return EditFragment().apply {
                arguments = args
            }
        }
    }
}
