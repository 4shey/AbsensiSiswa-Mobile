package com.zahra.absensisiswa.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahra.absensisiswa.Helper.AppState
import com.zahra.absensisiswa.Helper.Screen
import com.zahra.absensisiswa.Repo.AuthRepository
import kotlinx.coroutines.launch

object LoginViewModel : ViewModel() {

    var nis by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateNis(newNis: String) {
        nis = newNis
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun login(onError: (String) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val result = AuthRepository.loginSiswa(nis, password)

                if (result != null && result.success) {
                    AppState.currentScreen.value = Screen.HOME
                } else {
                    errorMessage = "Login gagal"
                    onError("Login gagal, pastikan nis dan password anda benar")
                }

            } catch (e: Exception) {
                errorMessage = "Terjadi kesalahan"
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}