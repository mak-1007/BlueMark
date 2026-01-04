package com.example.attendancemarker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancemarker.data.dao.AttendanceDao
import com.example.attendancemarker.data.dao.StudentDao
import com.example.attendancemarker.ui.theme.BluetoothController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
    private val studentDao: StudentDao,
    private val attendanceDao: AttendanceDao
): ViewModel() {

        private val _state = MutableStateFlow(BluetoothUiState())
        val state = combine(
            bluetoothController.scannedDevice,
            bluetoothController.pairedDevice,
            _state
        ){ scannedDevices, pairedDevices, state->

            val scannedMacs = scannedDevices.map { it.address }
            val allStudents = studentDao.getAll()

            // Match MACs
            val presentStudents1 = allStudents.filter { student ->
                scannedMacs.any { mac -> mac.equals(student.macAddress, ignoreCase = true) }
            }

            state.copy(
                scannedDevices = scannedDevices,
                pairedDevices = pairedDevices,
                presentStudents = presentStudents1
            )

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    fun startScan(){
        bluetoothController.startDiscovery()
    }

    fun stopScan(){
        bluetoothController.stopDiscovery()
    }

}