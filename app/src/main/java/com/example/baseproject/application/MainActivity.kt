package com.example.baseproject.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.baseproject.core.navigation.AppNavHost
import com.example.baseproject.ui.theme.BaseProjectTheme
import dagger.hilt.EntryPoint
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
    BaseProjectTheme {
        // A surface container using the 'background' color from the theme
        AppNavHost(
            navController = rememberNavController()
        )
    }
}