package com.zahra.absensisiswa.Helper

import androidx.compose.runtime.Composable
import com.zahra.absensisiswa.feature.home.HomeScreen
import com.zahra.absensisiswa.feature.home.HomeViewModel
import com.zahra.absensisiswa.feature.login.LoginScreen
import com.zahra.absensisiswa.feature.login.LoginViewModel
import com.zahra.absensisiswa.feature.profile.ProfileScreen
import com.zahra.absensisiswa.feature.profile.ProfileViewModel
import com.zahra.absensisiswa.feature.riwayat.RiwayatScreen
import com.zahra.absensisiswa.feature.riwayat.RiwayatViewModel

@Composable
fun AppScreen(
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel,
    riwayatViewModel: RiwayatViewModel
) {
    when (AppState.currentScreen.value) {
        Screen.LOGIN -> LoginScreen(loginViewModel)
        Screen.HOME -> HomeScreen(homeViewModel)
        Screen.RIWAYAT -> RiwayatScreen(riwayatViewModel)
        Screen.PROFILE -> ProfileScreen(profileViewModel)
    }
}