package com.zahra.absensisiswa.feature.riwayat

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zahra.absensisiswa.Helper.AppState
import com.zahra.absensisiswa.Helper.Screen
import com.zahra.absensisiswa.component.RiwayatCard
import com.zahra.absensisiswamobile.ui.theme.AppBlue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RiwayatScreen(viewModel: RiwayatViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getHistory()
    }
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Riwayat Absensi",
                    color = AppBlue,
                    fontWeight = FontWeight.Black

                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                if (viewModel.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(bottom = 130.dp)
                    ) {
                        items(viewModel.riwayat) { riwayats ->
                            RiwayatCard(
                                riwayat = riwayats,
                                onClick = { println("click") }
                            )
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