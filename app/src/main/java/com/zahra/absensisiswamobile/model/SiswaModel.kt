package com.zahra.absensisiswa.model

data class SiswaModel(
    val userID: Int,
    val nis: String,
    val nisn: String,
    val gender: String,
    val dateOfBirth: String,
    val address: String,
    val namaKelas: String,
    val kodeKelas: String,
    val username: String,
    val email: String,
    val role: String,
    val fullName: String,
    val referenceID: Int
)

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val user: LoginUser,
    val idKelas: Int
)

data class LoginUser(
    val userID: Int,
    val username: String,
    val role: String,
    val fullName: String,
    val referenceID: Int
)
