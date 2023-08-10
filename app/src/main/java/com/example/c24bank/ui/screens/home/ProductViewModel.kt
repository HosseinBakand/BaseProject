package com.example.c24bank.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24bank.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    productRepository: ProductRepository
) : ViewModel() {

    private val _filterState = MutableStateFlow(Filter.ALL)
    val filterState: StateFlow<Filter>
        get() = _filterState

    val uiState: StateFlow<ProductUiState> =
        combine(
            productRepository.products,
            filterState
        ) { products, filter ->
            val filteredProducts = when(filter){
                Filter.ALL -> products
                Filter.AVAILABLE -> products.filter { it.isAvailable }
                Filter.FAVORITE -> products.filter { it.isFavorite }
            }
            ProductUiState(
                products = filteredProducts
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductUiState()
        )

    init {
        viewModelScope.launch {
            productRepository.getProducts()
        }
    }

    fun filterList(filter: Filter) {
        _filterState.update { filter }
    }
}

enum class Filter(val text: String) {
    ALL("Alle"),
    AVAILABLE("Verfügbar"),
    FAVORITE("Vorgemerkt"),
}