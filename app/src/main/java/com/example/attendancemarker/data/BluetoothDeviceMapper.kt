package com.example.attendancemarker.data


import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.attendancemarker.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}