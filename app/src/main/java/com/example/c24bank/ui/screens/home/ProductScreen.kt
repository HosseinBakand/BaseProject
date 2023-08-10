package com.example.c24bank.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.c24bank.domain.model.Product
import com.example.c24bank.ui.theme.C24BankTheme

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel(), navigateToB: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductScreen(
        uiState = uiState
    ){
        navigateToB()
    }
}

@Composable
fun ProductScreen(uiState: ProductUiState, onItemClick : (Int)->Unit) {
    Column {
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
        Button(modifier = Modifier.size(100.dp), onClick = {}) {
            Text(text = "Screen B")
        }
    }
}

@Composable
fun ProductComponent(product: Product, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.large)
            .background(
                if (product.isFavorite) Color(0xFFD1C4E9) else Color.White,
                shape = MaterialTheme.shapes.large
            )
            .clip(shape = MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(text = product.name)
        Text(text = product.shortDescription)
        Text(text = product.price.toString())
        Text(text = product.rating.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    C24BankTheme {

    }
}