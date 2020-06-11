package com.peterdpong.mintask.addtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.mintask.TaskRepository
import com.peterdpong.mintask.models.Task
import java.util.*

class AddFragmentViewModel: ViewModel() {
    var currentTask: Task = Task()

    private val taskRepository = TaskRepository.get()

    fun addTask(task: Task){
        taskRepository.addTask(task)
    }

}