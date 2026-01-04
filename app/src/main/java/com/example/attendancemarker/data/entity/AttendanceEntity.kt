package com.example.attendancemarker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "attendance")
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rollNo: String,
    val name: String,
    val status: String,
    val timeStamp: Long = System.currentTimeMillis()
)
