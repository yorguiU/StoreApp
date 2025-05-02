package com.yorguisanchez.storeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.yorguisanchez.storeapp.ui.theme.StoreAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreAppTheme {

                val myNavController = rememberNavController()
                var myStartDestination: String = "Login"

                val auth = Firebase.auth
                val currentUser = auth.currentUser

                if(currentUser != null){
                    myStartDestination = "Home"
                }else{
                    myStartDestination = "login"
                }

                NavHost(
                    navController = myNavController,
                    startDestination = myStartDestination,
                ) {
                    composable(route = "login") {
                        LoginScreen(myNavController)
                    }
                    composable(route = "Register") {
                        RegisterScreen(myNavController)
                    }
                    composable(route = "Home") {
                        HomeScreen(myNavController)
                    }
                }
            }
        }
    }
}

