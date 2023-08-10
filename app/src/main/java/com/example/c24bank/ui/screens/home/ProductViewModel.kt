package com.example.c24bank.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24bank.data.repositories.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    sampleRepository: SampleRepository
) : ViewModel() {
    init{
        viewModelScope.launch {
            sampleRepository.nothing()
        }
    }
}