package com.peterdpong.mintask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peterdpong.mintask.models.Task

@Database(entities = [ Task::class ], version = 1)
@TypeConverters(TasksTypeConverters::class)
abstract class TasksDatabase: RoomDatabase(){

    abstract fun tasksDao(): TasksDao

}