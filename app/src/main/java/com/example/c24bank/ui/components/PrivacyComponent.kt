package com.example.c24bank.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PrivacyComponent() {
    Text(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                //TODO WEBPAGE
            }.padding(4.dp)
            ,
        text = "© 2016 Check24",

        )
}