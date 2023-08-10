package com.example.c24bank.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24bank.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState>
        get() = _uiState

    init {
        viewModelScope.launch {


            productRepository.getProducts()
            productRepository.products.onEach { products ->
                _uiState.update {
                    it.copy(
                        products = products
                    )
                }
            }.launchIn(viewModelScope)
        }
    }
}

enum class Filter(val text: String) {
    ALL("Alle"),
    AVAILABLE("Verfügbar"),
    FAVORITE("Vorgemerkt"),
}