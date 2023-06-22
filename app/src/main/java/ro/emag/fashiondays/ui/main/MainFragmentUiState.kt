package ro.emag.fashiondays.ui.main

import ro.emag.models.Products

sealed class MainFragmentUiState {
    object Loading : MainFragmentUiState()
    data class Success(val products: Products) : MainFragmentUiState()
    data class Error(val error: MainFragmentUiError) : MainFragmentUiState()
}