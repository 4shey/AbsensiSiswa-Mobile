package com.zahra.absensisiswa.Repo

import com.zahra.absensisiswa.Helper.BASE_URL
import com.zahra.absensisiswa.model.RiwayatModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object AbsenRepository {
    suspend fun postAbsensiByKode(
        kode: String,
        latitude: Double,
        longtitude: Double,
        keterangan: String,
    ): Boolean = withContext(Dispatchers.IO) {
        val url = URL("${BASE_URL}absensi/${kode}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true

        val jsonBody = JSONObject().apply {
            put("latitude", latitude)
            put("longtitude", longtitude)
            put("keterangan", keterangan)
        }

        connection.outputStream.use { os ->
            os.write(jsonBody.toString().toByteArray())
        }

        connection.responseCode in 200..299
    }

    suspend fun getHistory(): List<RiwayatModel> = withContext(Dispatchers.IO) {
        val url = URL("${BASE_URL}absensi/history")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val response = connection.inputStream.bufferedReader().readText()

        val jsonArray = JSONArray(response)
        val riwayat = mutableListOf<RiwayatModel>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val sesiObj = obj.getJSONObject("sesiAbsen")
            val kodeSesi = sesiObj.getString("kodeSesi")
            val guruObj = sesiObj.getJSONObject("guru")
            val namaGuru = guruObj.getString("fullName")
            riwayat.add(
                RiwayatModel(
                    idAbsensi = obj.getInt("absensiID"),
                    kodeSesi = kodeSesi,
                    waktuAbsen = obj.getString("waktuAbsen"),
                    namaGuru = namaGuru,
                    status = obj.getString("status")
                )
            )
        }

        riwayat
    }
}