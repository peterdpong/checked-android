package com.peterdpong.checked.addtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.checked.TaskRepository
import com.peterdpong.checked.models.Task

class AddFragmentViewModel: ViewModel() {
    var currentTask: Task = Task()

    private val taskRepository = TaskRepository.get()

    fun addTask(task: Task){
        taskRepository.addTask(task)
    }

}