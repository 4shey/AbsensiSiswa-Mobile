package com.zahra.absensisiswa.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zahra.absensisiswamobile.ui.theme.AppBlue

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val context = LocalContext.current

    // State error untuk masing-masing field
    var nisError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Judul
            Text(
                text = "Login Siswa",
                style = MaterialTheme.typography.headlineSmall,
                color = AppBlue,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // NIS Input
            OutlinedTextField(
                value = viewModel.nis,
                onValueChange = {
                    viewModel.updateNis(it)
                    nisError = null
                },
                label = { Text("NIS") },
                placeholder = { Text("Masukkan NIS") },
                isError = nisError != null,
                modifier = Modifier.fillMaxWidth()
            )
            nisError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.updatePassword(it)
                    passwordError = null
                },
                label = { Text("Password") },
                placeholder = { Text("Masukkan password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError != null,
                modifier = Modifier.fillMaxWidth()
            )
            passwordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    // Reset error
                    nisError = null
                    passwordError = null

                    var valid = true
                    if (viewModel.nis.isBlank()) {
                        nisError = "NIS tidak boleh kosong"
                        valid = false
                    }
                    if (viewModel.password.isBlank()) {
                        passwordError = "Password tidak boleh kosong"
                        valid = false
                    }

                    if (valid) {
                        viewModel.login(
                            onError = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !viewModel.isLoading,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppBlue,
                    contentColor = Color.White
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Loading...")
                    } else {
                        Text("Login")
                    }
                }
            }
        }
    }
}