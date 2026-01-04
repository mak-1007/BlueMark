package com.example.attendancemarker.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancemarker.data.Repository.StudentRepository
import com.example.attendancemarker.data.entity.StudentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    fun saveStudents(students: List<StudentEntity>) {
        viewModelScope.launch {
            studentRepository.insertAll(students)
        }
    }
}
