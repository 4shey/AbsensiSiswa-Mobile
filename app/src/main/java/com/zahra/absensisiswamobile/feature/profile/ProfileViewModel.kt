package com.zahra.absensisiswa.feature.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahra.absensisiswa.Repo.AbsenRepository
import com.zahra.absensisiswa.Repo.AuthRepository
import com.zahra.absensisiswa.model.RiwayatModel
import com.zahra.absensisiswa.model.SiswaModel
import kotlinx.coroutines.launch

object ProfileViewModel : ViewModel() {
    var profile by mutableStateOf<SiswaModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun getProfile() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = AuthRepository.getProfile()
                profile = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}