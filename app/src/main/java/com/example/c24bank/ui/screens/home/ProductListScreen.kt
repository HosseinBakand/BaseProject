package com.example.c24bank.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.c24bank.R
import com.example.c24bank.domain.model.Filter
import com.example.c24bank.domain.model.NetworkRequestState
import com.example.c24bank.domain.model.Product
import com.example.c24bank.domain.model.previewProducts
import com.example.c24bank.ui.components.RatingComponent
import com.example.c24bank.ui.theme.C24BankTheme

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel = hiltViewModel(), navigateToDetail: (productId: Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductListScreen(
        uiState = uiState,
        onFilterClick = viewModel::filterList,
        onItemClick = navigateToDetail,
        onRefreshRequest = viewModel::fetchData
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListScreen(
    uiState: ProductUiState,
    onRefreshRequest: () -> Unit,
    onFilterClick: (Filter) -> Unit,
    onItemClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = uiState.requestState is NetworkRequestState.Error) {
            if (uiState.requestState is NetworkRequestState.Error) {
                val error = uiState.requestState.exception.message
                ErrorComponent(text = error.toString(), onRefreshRequest = onRefreshRequest)
            }
        }
        val filters = remember { Filter.values() }
        MBTabRow(selectedTabIndex = Filter.values().indexOf(uiState.filter)) {
            filters.forEach {
                MBTab(
                    selected = it == uiState.filter,
                    onClick = { onFilterClick(it) },
                    text = { Text(text = it.text) },
                )
            }
        }

        Text(text = "Check24 Shape Compararison")
        Text(text = "List of geometric products")

        val pullRefreshState = rememberPullRefreshState(uiState.isLoading, onRefreshRequest)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {

                items(items = uiState.products, key = { it.id }) {
                    ProductComponent(
                        product = it
                    ) {
                        onItemClick(it.id)
                    }

                }
            }
            PullRefreshIndicator(
                uiState.isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter)
            )
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
                else MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.large
            )
            .clip(shape = MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        if (product.isAvailable) {
            AsyncImage(
                modifier = Modifier
                    .size(75.dp),
                model = product.imageUrl,
                contentDescription = null,
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
                text = product.shortDescription, style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (product.isAvailable) {
                    Text(
                        text = "${product.price}", style = MaterialTheme.typography.bodySmall
                    )
                }
                RatingComponent(rating = product.rating.toFloat())
            }
        }
        if (!product.isAvailable) {
            AsyncImage(
                modifier = Modifier
                    .size(75.dp),
                model = product.imageUrl,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ErrorComponent(text: String, onRefreshRequest: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
            tint = Color.Red
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onRefreshRequest
            ) {
                Text(
                    text = "Neuladen",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MBTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Tab(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        text = {
            val style = MaterialTheme.typography.labelLarge.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(
                value = style,
                content = {
                    Box(modifier = Modifier.padding(top = 8.dp)) {
                        text()
                    }
                },
            )
        },
    )
}

@Composable
fun MBTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        tabs = tabs,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun Preview() {
    C24BankTheme {
        Scaffold {
            ProductListScreen(
                uiState = ProductUiState(previewProducts),
                onRefreshRequest = {},
                onItemClick = {},
                onFilterClick = {}
            )
        }
    }
}
