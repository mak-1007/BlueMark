package com.example.attendancemarker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.attendancemarker.data.dao.AttendanceDao
import com.example.attendancemarker.data.dao.StudentDao
import com.example.attendancemarker.data.entity.AttendanceEntity
import com.example.attendancemarker.data.entity.StudentEntity


@Database(entities = [StudentEntity::class, AttendanceEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun StudentDao(): StudentDao

    abstract fun attendanceDap(): AttendanceDao

}