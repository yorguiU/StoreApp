package com.yorguisanchez.storeapp

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(navController: NavController) {

    //ESTADOS
    //var ImputEmail = "ysanchez192@unab.edu.co"
    var inputEmail by remember { mutableStateOf(value = "") }
    var inputPassword by remember { mutableStateOf(value = "") }

    val activity = LocalView.current.context as Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Icono de usuario",
            modifier = Modifier
                .size(100.dp),
            tint = Color(0xFFFF9800)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Iniciar sesión",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = inputEmail,
            onValueChange = { inputEmail = it },
            label = { Text("Correo Electrónico") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Correo")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputPassword,
            onValueChange = { inputPassword = it },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val auth = Firebase.auth


                auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {
                            navController.navigate("Home")

                        } else {
                            Toast.makeText(
                                activity.applicationContext,
                                "Error en credencialwa",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800)
            )
        ) {
            Text("Iniciar sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(route = "Register")
        }) {
            Text(
                "¿No tienes cuenta? Regístrate",
                color = Color(color = (0xFFFF9800))
            )
        }
    }
}