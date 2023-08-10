package com.example.c24bank.domain.model

import java.lang.Exception

sealed class NetworkRequestState {
    object Success : NetworkRequestState()
    object Loading : NetworkRequestState()
    class Error(val exception: Exception) : NetworkRequestState()
}