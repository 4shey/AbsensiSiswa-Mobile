package com.zahra.absensisiswa.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zahra.absensisiswa.Helper.AppState
import com.zahra.absensisiswa.Helper.Screen
import com.zahra.absensisiswa.model.RiwayatModel
import com.zahra.absensisiswamobile.ui.theme.AppBlue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RiwayatCard(
    riwayat: RiwayatModel,
    onClick: (Int) -> Unit
) {

    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val formattedDate = try {
        val dateTime = LocalDateTime.parse(riwayat.waktuAbsen, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        "-"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        onClick = {

        },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppBlue
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "${riwayat.kodeSesi}",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White)
                )
                Text(
                    text = formattedDate,
                    fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text("Guru", fontWeight = FontWeight.Medium, color = Color.White)
                    Text(
                        "${riwayat.namaGuru}",fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
                val statusColor = when (riwayat.status) {
                    "Alpha" -> Color.Red
                    "Hadir" -> Color.Green
                    "Izin", "Sakit" -> Color.Black
                    else -> Color.Gray
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = statusColor
                    )
                ) {
                    Text("${riwayat.status}", fontWeight = FontWeight.Black)
                }
            }
        }
    }
}
