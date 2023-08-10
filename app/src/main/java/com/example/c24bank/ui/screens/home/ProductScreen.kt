package com.example.c24bank.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.c24bank.R
import com.example.c24bank.domain.model.Product
import com.example.c24bank.domain.model.previewProducts
import com.example.c24bank.ui.components.RatingComponent
import com.example.c24bank.ui.theme.C24BankTheme

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel(), navigateToB: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductScreen(
        uiState = uiState
    ) {
        navigateToB()
    }
}

@Composable
fun ProductScreen(uiState: ProductUiState, onItemClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Title....")
        Text(text = "Subtitle...")
        Text(text = "Filter1,  Filter2,  Filter3, ")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
        ) {
            items(items = uiState.products) {
                ProductComponent(
                    product = it
                ) {
                    onItemClick(1)
                }
            }
        }
    }
}

@Composable
fun ProductComponent(product: Product, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            )
            .background(
                if (product.isFavorite) Color(0xFFD1C4E9)
                else MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large
            )
            .clip(shape = MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        if (product.isAvailable) {
            AsyncImage(
                modifier = Modifier.height(75.dp),
                model = product.imageUrl,
                contentDescription = null,
                alignment = Alignment.CenterEnd,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                )
                if (product.isAvailable) {
                    Text(
                        text = product.releaseDate.toString(),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (product.isAvailable) {
                    Text(
                        text = "${product.price}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                RatingComponent(rating = product.rating.toFloat())
            }
        }
        if (!product.isAvailable) {
            AsyncImage(
                modifier = Modifier.height(75.dp),
                model = product.imageUrl,
                contentDescription = null,
                alignment = Alignment.CenterEnd,
            )
        }
    }
}

