package com.example.c24bank.ui.screens.temp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24bank.data.repositories.ProductRepository
import com.example.c24bank.domain.model.Product
import com.example.c24bank.ui.navigation.DetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val productId: Int = savedStateHandle[DetailDestination.productIdArg] ?: -1

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() = _uiState

    var productObserverJob: Job? = null

    init {
        if (productId != -1) {

            Log.e("TAGTAG", productId.toString())
            getProduct(productId)
        } else {
            //TODO show ERROR
        }
    }

    private fun getProduct(productId: Int) {
        productObserverJob?.cancel()
        productObserverJob = productRepository.getProduct(productId).onEach { product ->
            _uiState.update { it.copy(product = product) }
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            uiState.value.product?.let { product ->
                if (product.isFavorite) {
                    productRepository.deleteFavorite(productId)
                } else {
                    productRepository.addFavorite(productId)
                }
            }
        }

    }
}

data class DetailUiState(
    val product: Product? = null
)
