package com.zahra.absensisiswa.Repo

import com.zahra.absensisiswa.Helper.BASE_URL
import com.zahra.absensisiswa.model.LoginResponse
import com.zahra.absensisiswa.model.LoginUser
import com.zahra.absensisiswa.model.SiswaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object AuthRepository {

    suspend fun loginSiswa(
        nis: String,
        password: String
    ): LoginResponse? = withContext(Dispatchers.IO) {

        val url = URL("${BASE_URL}auth/siswa/login")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true

        val jsonBody = JSONObject().apply {
            put("nis", nis)
            put("password", password)
        }

        connection.outputStream.use { os ->
            os.write(jsonBody.toString().toByteArray())
        }

        if (connection.responseCode !in 200..299) return@withContext null

        val response = connection.inputStream.bufferedReader().readText()
        val json = JSONObject(response)

        val userObj = json.getJSONObject("user")

        LoginResponse(
            success = json.getBoolean("success"),
            token = json.getString("token"),
            idKelas = json.getInt("idKelas"),
            user = LoginUser(
                userID = userObj.getInt("userID"),
                username = userObj.getString("username"),
                role = userObj.getString("role"),
                fullName = userObj.getString("fullName"),
                referenceID = userObj.getInt("referenceID"),
            )
        )
    }

    suspend fun getProfile(): SiswaModel? = withContext(Dispatchers.IO) {

        val url = URL("${BASE_URL}auth/profile")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
//        connection.setRequestProperty("Authorization", "Bearer $token")
        connection.setRequestProperty("Accept", "application/json")

        if (connection.responseCode !in 200..299) return@withContext null

        val response = connection.inputStream.bufferedReader().readText()
        val json = JSONObject(response)

        SiswaModel(
            userID = json.getInt("userID"),
            nis = json.getString("nis"),
            nisn = json.getString("nisn"),
            gender = json.getString("gender"),
            dateOfBirth = json.getString("dateOfBirth"),
            address = json.getString("address"),
            namaKelas = json.getString("namaKelas"),
            kodeKelas = json.getString("kodeKelas"),
            username = json.getString("username"),
            email = json.getString("email"),
            role = json.getString("role"),
            fullName = json.getString("fullName"),
            referenceID = json.getInt("referenceID")
        )
    }
}