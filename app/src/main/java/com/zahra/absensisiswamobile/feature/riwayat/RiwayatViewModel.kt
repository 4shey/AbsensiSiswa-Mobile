package com.zahra.absensisiswa.feature.riwayat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahra.absensisiswa.Repo.AbsenRepository
import com.zahra.absensisiswa.model.RiwayatModel
import kotlinx.coroutines.launch

object RiwayatViewModel : ViewModel() {

    var riwayat by mutableStateOf<List<RiwayatModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun getHistory() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = AbsenRepository.getHistory()
                riwayat = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}