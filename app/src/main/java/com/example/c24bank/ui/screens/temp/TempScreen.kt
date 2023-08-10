package com.example.c24bank.ui.screens.temp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(){
    DetailScreen(1)
}
@Composable
fun DetailScreen(i : Int){
    Column {
        Text(text = "Name")
        Text(text = "Description")
        Text(text = "Price")
        Text(text = "Rating")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Favorite")
        }
        Text(text = "Description")
    }
}