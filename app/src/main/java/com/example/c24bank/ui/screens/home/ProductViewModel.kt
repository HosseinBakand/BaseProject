package com.example.c24bank.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24bank.domain.model.Filter
import com.example.c24bank.domain.model.Header
import com.example.c24bank.domain.model.NetworkRequestState
import com.example.c24bank.domain.model.Product
import com.example.c24bank.domain.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _filterState = MutableStateFlow(Filter.ALL)
    private val _loadingState: MutableStateFlow<NetworkRequestState> =
        MutableStateFlow(NetworkRequestState.Loading)

    val uiState: StateFlow<ProductUiState> =
        combine(
            productRepository.header,
            productRepository.products,
            _filterState,
            _loadingState
        ) { header, products, filter, isLoading ->
            val filteredProducts = when (filter) {
                Filter.ALL -> products
                Filter.AVAILABLE -> products.filter { it.isAvailable }
                Filter.FAVORITE -> products.filter { it.isFavorite }
            }
            ProductUiState(
                header = header,
                products = filteredProducts,
                requestState = isLoading,
                filter = filter
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductUiState()
        )


    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _loadingState.update { NetworkRequestState.Loading }
            productRepository.getProducts().let { result ->
                _loadingState.update { result }
            }
        }
    }

    fun filterList(filter: Filter) {
        _filterState.update { filter }
    }
}

data class ProductUiState(
    val header: Header? = null,
    val products: List<Product> = emptyList(),
    val requestState: NetworkRequestState = NetworkRequestState.Loading,
    val filter: Filter = Filter.ALL
) {
    val isLoading: Boolean
        get() = requestState == NetworkRequestState.Loading
}
