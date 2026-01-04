package com.example.attendancemarker.presentation

import com.example.attendancemarker.data.entity.StudentEntity
import com.example.attendancemarker.domain.BluetoothDevice

data class BluetoothUiState (
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val presentStudents: List<StudentEntity> = emptyList()
)