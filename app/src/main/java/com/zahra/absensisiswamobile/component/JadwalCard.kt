package com.zahra.absensisiswa.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zahra.absensisiswa.model.JadwalModel
import com.zahra.absensisiswamobile.ui.theme.AppBlue

@Composable
fun JadwalCard(
    jadwal: JadwalModel,
    onClick: (Int) -> Unit
) {
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
            Text(
                text = "${jadwal.namaMapel}",fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Guru",fontWeight = FontWeight.Medium, color = Color.White)
                    Text(
                        "${jadwal.namaGuru}",fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text("Dari : ${jadwal.dari}",fontWeight = FontWeight.Medium, color = Color.White)
                    Text("Sampai : ${jadwal.sampai}",fontWeight = FontWeight.Medium, color = Color.White)
                }
            }
        }
    }
}