package com.zahra.absensisiswamobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zahra.absensisiswa.Helper.AppScreen
import com.zahra.absensisiswa.feature.home.HomeViewModel
import com.zahra.absensisiswa.feature.login.LoginViewModel
import com.zahra.absensisiswa.feature.profile.ProfileViewModel
import com.zahra.absensisiswa.feature.riwayat.RiwayatViewModel
import com.zahra.absensisiswamobile.ui.theme.AbsensiSiswaMobileTheme

class MainActivity : ComponentActivity() {
    private val loginViewModel = LoginViewModel
    private val homeViewModel = HomeViewModel
    private val riwayatViewModel = RiwayatViewModel
    private val profileViewModel = ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbsensiSiswaMobileTheme() {
                AppScreen(loginViewModel, homeViewModel, profileViewModel, riwayatViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AbsensiSiswaMobileTheme {
        Greeting("Android")
    }
}