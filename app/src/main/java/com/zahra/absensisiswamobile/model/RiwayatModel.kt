package com.zahra.absensisiswa.model

data class RiwayatModel(
    val idAbsensi: Int,
    val kodeSesi: String,
    val waktuAbsen: String,
    val namaGuru: String,
    val status: String
)