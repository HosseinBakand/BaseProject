package com.example.c24bank.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.c24bank.ui.navigation.AppNavHost
import com.example.c24bank.ui.theme.C24BankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApp()
        }
    }
}

@Composable
fun ComposeApp() {
    C24BankTheme {
        // A surface container using the 'background' color from the theme
        AppNavHost(
            navController = rememberNavController()
        )
    }
}