package com.example.attendancemarker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val rollNo: String,
    val name: String,
    val bluetoothName: String,
    val macAddress: String
)