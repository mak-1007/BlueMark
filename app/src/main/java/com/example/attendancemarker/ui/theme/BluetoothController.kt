package com.example.attendancemarker.ui.theme

import com.example.attendancemarker.domain.BluetoothDeviceDomain
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevice: StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevice: StateFlow<List<BluetoothDeviceDomain>>

    fun startDiscovery()
    fun stopDiscovery()

    fun release()
}