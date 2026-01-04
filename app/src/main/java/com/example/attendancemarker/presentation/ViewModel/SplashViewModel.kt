package com.example.attendancemarker.presentation.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    var isSplashVisible = mutableStateOf(true)

    init {
        viewModelScope.launch {
            delay(2000)
            isSplashVisible.value = false
        }
    }
}
