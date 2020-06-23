package com.peterdpong.checked

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.peterdpong.checked.database.TasksDao
import com.peterdpong.checked.database.TasksDatabase
import com.peterdpong.checked.database.migration_1_2
import com.peterdpong.checked.models.Task
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "task-database"

class TaskRepository private constructor(context: Context){

    //Repository properties
    private val database: TasksDatabase = Room.databaseBuilder(
        context.applicationContext,
        TasksDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

    private val taskDao: TasksDao = database.tasksDao()
    private val executor = Executors.newSingleThreadExecutor()


    fun getTasks(): LiveData<List<Task>> = taskDao.getTasks()

    fun getTaskFromId(id: UUID): LiveData<Task?> = taskDao.getTaskFromId(id)

    fun getCount(): LiveData<Int> = taskDao.getCount()

    fun updateTask(task: Task) {
        executor.execute {
            taskDao.updateTask(task)
        }
    }

    fun addTask(task: Task) {
        executor.execute {
            taskDao.addTask(task)
        }
    }

    fun deleteTask(task: Task){
        executor.execute {
            taskDao.deleteTask(task)
        }
    }

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