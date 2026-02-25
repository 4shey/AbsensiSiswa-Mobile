package com.zahra.absensisiswa.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zahra.absensisiswa.Helper.AppState
import com.zahra.absensisiswa.Helper.Screen
import com.zahra.absensisiswamobile.ui.theme.AppBlue
import java.time.format.TextStyle
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Profile",
                    color = AppBlue,
                    fontWeight = FontWeight.Black
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = AppState.currentScreen.value == Screen.HOME,
                    onClick = { AppState.currentScreen.value = Screen.HOME },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home", fontWeight = FontWeight.Bold) }
                )

                NavigationBarItem(
                    selected = AppState.currentScreen.value == Screen.PROFILE,
                    onClick = { },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile", fontWeight = FontWeight.Bold) }
                )

                NavigationBarItem(
                    selected = AppState.currentScreen.value == Screen.RIWAYAT,
                    onClick = { AppState.currentScreen.value = Screen.RIWAYAT },
                    icon = { Icon(Icons.Default.DateRange, null) },
                    label = { Text("Riwayat", fontWeight = FontWeight.Bold) }
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Spacer(Modifier.height(20.dp))

                    viewModel.profile?.let { siswa ->

                        Text("Nama Lengkap")
                        Text(siswa.fullName, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("NIS")
                        Text(siswa.nis, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("NISN")
                        Text(siswa.nisn, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("Kelas")
                        Text(siswa.namaKelas, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("Gender")
                        Text(siswa.gender, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("Email")
                        Text(siswa.email, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Text("Username")
                        Text(siswa.username, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(20.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            AppState.currentScreen.value = Screen.LOGIN
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Logout")
                    }
                }
            }
        }
    }
}