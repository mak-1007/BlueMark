package com.example.attendancemarker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.attendancemarker.data.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(student: List<StudentEntity>)


    @Query("SELECT * FROM STUDENTS")
    suspend fun getAll(): List<StudentEntity>
}