package com.peterdpong.checked.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.peterdpong.checked.models.Task
import java.util.*

@Dao
interface TasksDao {
    @Query("SELECT * FROM task")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getTaskFromId(id: UUID): LiveData<Task?>

    @Query("SELECT COUNT(*) FROM task")
    fun getCount(): LiveData<Int>

    @Update
    fun updateTask(task: Task)

    @Insert
    fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}