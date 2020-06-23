package com.peterdpong.checked.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.peterdpong.checked.models.Task

@Database(entities = [ Task::class ], version = 2)
@TypeConverters(TasksTypeConverters::class)
abstract class TasksDatabase: RoomDatabase(){

    abstract fun tasksDao(): TasksDao

}

val migration_1_2 = object: Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Task ADD COLUMN notification INTEGER NOT NULL DEFAULT 'false'"
        )
        database.execSQL(
            "ALTER TABLE Task ADD COLUMN notificationID TEXT NOT NULL DEFAULT ''"
        )
    }
}