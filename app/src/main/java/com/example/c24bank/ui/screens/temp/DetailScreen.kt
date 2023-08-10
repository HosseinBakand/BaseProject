package com.example.c24bank.ui.screens.temp

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.c24bank.domain.model.previewProducts
import com.example.c24bank.ui.components.RatingComponent
import com.example.c24bank.ui.theme.C24BankTheme

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailScreen(uiState, onFavoriteClick = viewModel::toggleFavorite)
}

@Composable
fun DetailScreen(uiState: DetailUiState, onFavoriteClick: () -> Unit) {
    if (uiState.product != null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(125.dp)
                        .padding(8.dp),
                    model = uiState.product.imageUrl,
                    contentDescription = null,
                    alignment = Alignment.CenterEnd,
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val textColor by
                    animateColorAsState(
                        targetValue = if (uiState.product.isFavorite) Color.Blue
                        else Color.Black
                    )
                    Text(
                        text = uiState.product.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = textColor
                    )
                    Text(
                        text = "${uiState.product.price}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingComponent(rating = uiState.product.rating.toFloat())
                        Text(
                            text = uiState.product.releaseDate.toString(),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }

            Text(
                text = uiState.product.shortDescription,
                style = MaterialTheme.typography.bodyMedium
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onFavoriteClick
            ) {
//                TODO AnimatedContent
                Text(
                    text = if (uiState.product.isFavorite) "Vergessen" else "Vormerken",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = uiState.product.longDescription,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun Preview() {
    C24BankTheme {
        Scaffold { _ ->
            DetailScreen(uiState = DetailUiState(product = previewProducts.first())) {

            }
        }
    }
}