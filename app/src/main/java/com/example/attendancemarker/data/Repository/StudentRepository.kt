package com.example.attendancemarker.data.Repository

import com.example.attendancemarker.data.dao.StudentDao
import com.example.attendancemarker.data.entity.StudentEntity
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentDao: StudentDao
) {
    suspend fun insertAll(students: List<StudentEntity>) {
        studentDao.insertAll(students)
    }
}