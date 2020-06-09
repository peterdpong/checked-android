package com.peterdpong.mintask.listtasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.Hold
import com.peterdpong.mintask.R
import com.peterdpong.mintask.addtasks.AddFragment
import com.peterdpong.mintask.models.Task


class ListFragment : Fragment() {

    private lateinit var fabButton: FloatingActionButton
    private lateinit var taskRecyclerView: RecyclerView
    private var adapter: TaskAdapter? = TaskAdapter(emptyList())

    private val taskListViewModel: ListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        taskRecyclerView = view.findViewById(R.id.tasks_list)
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskRecyclerView.adapter = adapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListViewModel.taskList.observe(viewLifecycleOwner,
            Observer { tasks ->
            tasks?.let {
                updateList(tasks)
            }})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

    private fun updateList(tasks: List<Task>) {
        adapter = TaskAdapter(tasks)
        taskRecyclerView.adapter = adapter
    }


    //TaskAdapter for RecyclerView
    private inner class TaskAdapter(var tasks: List<Task>): RecyclerView.Adapter<TaskHolder>(){
        override fun getItemCount(): Int {
            return tasks.size
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = tasks[position]
            holder.onBind(task)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val view = layoutInflater.inflate(R.layout.task_list_item, parent, false)
            return TaskHolder(view)
        }
    }


    //TaskHolder for RecyclerView
    private inner class TaskHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var task: Task

        val taskTitleView: TextView = itemView.findViewById(R.id.task_title)
        val dateTextView: TextView = itemView.findViewById(R.id.task_date)
        val priorityTextView: TextView = itemView.findViewById(R.id.task_priority)

        fun onBind(task: Task){
            this.task = task
            taskTitleView.text = this.task.title
            dateTextView.text = this.task.dueDate.toString()
            priorityTextView.text = this.task.priorty
        }
    }

}
