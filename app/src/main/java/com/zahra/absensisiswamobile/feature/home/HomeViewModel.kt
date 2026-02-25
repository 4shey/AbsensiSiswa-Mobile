package com.zahra.absensisiswa.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahra.absensisiswa.Repo.AbsenRepository
import com.zahra.absensisiswa.Repo.JadwalRepository
import com.zahra.absensisiswa.model.JadwalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object HomeViewModel : ViewModel() {

    var kode by mutableStateOf("")
    var jadwal by mutableStateOf<List<JadwalModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun getJadwalToday() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = JadwalRepository.getJadwalToday()
                jadwal = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun postAbsensiByKode() {
        CoroutineScope(Dispatchers.Main).launch {
            val success = AbsenRepository.postAbsensiByKode(
                kode = kode,
                latitude = 1.2,
                longtitude = 1.2,
                keterangan = "-"
            )
            if (success) {
                println("Success")
            }
        }
    }
}