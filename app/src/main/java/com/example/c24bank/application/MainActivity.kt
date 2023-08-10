package com.example.c24bank.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.rememberNavController
import com.example.c24bank.ui.components.NiaTopAppBar
import com.example.c24bank.ui.components.PrivacyComponent
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
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                PrivacyComponent()
            },
            topBar = {
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        )
                    )
            ) {
                AppNavHost(
                    navController = rememberNavController()
                )
            }
        }
    }
}