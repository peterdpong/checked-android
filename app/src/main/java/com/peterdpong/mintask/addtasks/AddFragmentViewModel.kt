package com.peterdpong.mintask.addtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.mintask.TaskRepository
import com.peterdpong.mintask.models.Task

class AddFragmentViewModel: ViewModel() {
    private val taskRepository = TaskRepository.get()

    fun addTask(task: Task){
        taskRepository.addTask(task)
    }
}