package com.zahra.absensisiswa.Helper

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class Screen {
    HOME,
    PROFILE,
    RIWAYAT,
    LOGIN
}

object AppState {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.HOME)
}