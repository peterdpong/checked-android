package com.peterdpong.checked.listtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.checked.TaskRepository
import com.peterdpong.checked.models.Task

class ListFragmentViewModel: ViewModel() {
    private val taskRepository = TaskRepository.get()

    val taskList = taskRepository.getTasks()

    var taskListSize = taskRepository.getCount()

    fun deleteItem(task: Task){
        taskRepository.deleteTask(task)
    }

}