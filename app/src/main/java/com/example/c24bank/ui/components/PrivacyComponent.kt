package com.example.c24bank.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp

private const val PRIVACY_POLICY_URL = "http://m.check24.de/rechtliche-hinweise?deviceoutput=app"

@Composable
fun PrivacyComponent() {
    val uriHandler = LocalUriHandler.current

    Text(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                uriHandler.openUri(PRIVACY_POLICY_URL)
            }.padding(4.dp)
            ,
        text = "© 2016 Check24",

        )
}