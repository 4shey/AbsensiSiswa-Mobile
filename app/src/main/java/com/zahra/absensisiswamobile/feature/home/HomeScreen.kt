package com.zahra.absensisiswa.feature.home

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zahra.absensisiswa.Helper.AppState
import com.zahra.absensisiswa.Helper.Screen
import com.zahra.absensisiswa.component.JadwalCard
import com.zahra.absensisiswamobile.ui.theme.AppBlue

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getJadwalToday()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(100.dp)
                    .background(Color.White)
                    .padding(20.dp),
            ) {
                Spacer(Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Jadwal Hari Ini", fontWeight = FontWeight.Bold, color = AppBlue)
                    Button(
                        onClick = {
                            showDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Tambah Absensi")
                    }
                }
            }
            if (showDialog) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        OutlinedTextField(
                            value = viewModel.kode,
                            onValueChange = { viewModel.kode = it },
                            placeholder = { Text("Kode Absen") }
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.postAbsensiByKode()
                            showDialog = false
                            Toast.makeText(
                                context,
                                "Berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                        }) {
                            Text("Absen")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }
                        ) {
                            Text("Batal")
                        }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ){
                if (viewModel.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(modifier = Modifier.padding(bottom = 130.dp)) {
                        items(viewModel.jadwal) { jadwals ->
                            JadwalCard(jadwal = jadwals, onClick = {
                                println("click")
                            })
                        }
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        NavigationBar(
            containerColor = Color.White
        ) {

            NavigationBarItem(
                selected = AppState.currentScreen.value == Screen.HOME,
                onClick = { AppState.currentScreen.value = Screen.HOME },
                icon = {
                    Icon(Icons.Default.Home, contentDescription = "Home")
                },
                label = { Text("Home", fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = AppBlue,
                    indicatorColor = AppBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )

            NavigationBarItem(
                selected = AppState.currentScreen.value == Screen.PROFILE,
                onClick = { AppState.currentScreen.value = Screen.PROFILE },
                icon = {
                    Icon(Icons.Default.Person, contentDescription = "Profile")
                },
                label = { Text("Profile", fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = AppBlue,
                    indicatorColor = AppBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )

            NavigationBarItem(
                selected = AppState.currentScreen.value == Screen.RIWAYAT,
                onClick = { AppState.currentScreen.value = Screen.RIWAYAT },
                icon = {
                    Icon(Icons.Default.DateRange, contentDescription = "Riwayat")
                },
                label = { Text("Riwayat", fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = AppBlue,
                    indicatorColor = AppBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}