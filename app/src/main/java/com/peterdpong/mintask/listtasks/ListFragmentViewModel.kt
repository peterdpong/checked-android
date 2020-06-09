package com.peterdpong.mintask.listtasks

import androidx.lifecycle.ViewModel
import com.peterdpong.mintask.TaskRepository

class ListFragmentViewModel: ViewModel() {
    private val taskRepository =
        TaskRepository.get()

    val taskList = taskRepository.getTasks()

}