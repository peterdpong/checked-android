package com.peterdpong.mintask.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

enum class Priority{
    NONE,
    LOW,
    MEDIUM,
    HIGH
}

@Entity
data class Task(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var dueDate: Date = Date(),
                var priorty: Priority = Priority.NONE,
                var tags: MutableList<String> = mutableListOf())