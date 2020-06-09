package com.peterdpong.mintask.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Task(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var dueDate: Date = Date(),
                var priorty: String = "")