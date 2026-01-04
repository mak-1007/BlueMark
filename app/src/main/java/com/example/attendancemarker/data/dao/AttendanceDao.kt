package com.example.attendancemarker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.attendancemarker.data.entity.AttendanceEntity


@Dao
interface AttendanceDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendanceList(attendanceList: List<AttendanceEntity>)

    @Query("SELECT * FROM attendance ORDER BY timestamp DESC")
    suspend fun getAllAttendance(): List<AttendanceEntity>

    @Query("DELETE FROM attendance")
    suspend fun clearAttendance()
}