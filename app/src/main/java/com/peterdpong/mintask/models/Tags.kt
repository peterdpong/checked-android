package com.peterdpong.mintask.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Tags (@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var tagtitle: String = "")