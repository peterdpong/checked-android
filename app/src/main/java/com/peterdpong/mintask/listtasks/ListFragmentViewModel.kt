package com.peterdpong.mintask.listtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.mintask.TaskRepository
import com.peterdpong.mintask.models.Task

class ListFragmentViewModel: ViewModel() {
    private val taskRepository = TaskRepository.get()

    val taskList = taskRepository.getTasks()

    var taskListSize = taskRepository.getCount()

    fun deleteItem(task: Task){
        taskRepository.deleteTask(task)
    }

}