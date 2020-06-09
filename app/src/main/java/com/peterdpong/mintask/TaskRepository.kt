package com.peterdpong.mintask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.peterdpong.mintask.database.TasksDao
import com.peterdpong.mintask.database.TasksDatabase
import com.peterdpong.mintask.models.Task
import java.util.*

private const val DATABASE_NAME = "task-database"

class TaskRepository private constructor(context: Context){

    //Repository properties
    private val database: TasksDatabase = Room.databaseBuilder(
        context.applicationContext,
        TasksDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val taskDao: TasksDao = database.tasksDao()


    fun getTasks(): LiveData<List<Task>> = taskDao.getTasks()

    fun getTaskFromId(id: UUID): LiveData<Task?> = taskDao.getTaskFromId(id)


    companion object{
        private var INSTANCE: TaskRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = TaskRepository(context)
            }
        }

        fun get(): TaskRepository{
            return INSTANCE ?:
            throw IllegalStateException("Repository was not initialized")
        }

    }
}