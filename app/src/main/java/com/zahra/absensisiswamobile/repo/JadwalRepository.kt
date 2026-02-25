package com.zahra.absensisiswa.Repo

import com.zahra.absensisiswa.Helper.BASE_URL
import com.zahra.absensisiswa.model.JadwalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object JadwalRepository {

    suspend fun getJadwalToday(): List<JadwalModel> = withContext(Dispatchers.IO) {
        val url = URL("${BASE_URL}jadwal/today")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val response = connection.inputStream.bufferedReader().readText()

        val jsonObject = JSONObject(response)
        val jsonArray = jsonObject.getJSONArray("jadwal")
        val jadwal = mutableListOf<JadwalModel>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val mapelObj = obj.getJSONObject("mapel")
            val namaMapel = mapelObj.getString("namaMapel")
            val guruObj = obj.getJSONObject("guru")
            val namaGuru = guruObj.getString("fullName")
            val kelasObj = obj.getJSONObject("kelas")
            val namaKelas = kelasObj.getString("namaKelas")
            jadwal.add(
                JadwalModel(
                    jadwalId = obj.getInt("jadwalID"),
                    namaMapel = namaMapel,
                    namaGuru = namaGuru,
                    dari = obj.getString("jamMulai"),
                    sampai = obj.getString("jamSelesai"),
                )
            )
        }

        jadwal
    }
}