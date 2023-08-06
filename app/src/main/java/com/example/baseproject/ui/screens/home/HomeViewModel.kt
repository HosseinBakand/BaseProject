package com.example.baseproject.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseproject.data.repositories.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    sampleRepository: SampleRepository
) : ViewModel() {
    init{
        viewModelScope.launch {
            sampleRepository.nothing()
        }
    }
}