package ro.emag.fashiondays.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.emag.models.Product
import ro.emag.models.Products
import ro.emag.repo.FashionDaysRepo
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repo: FashionDaysRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<MainFragmentUiState>(MainFragmentUiState.Success(Products()))
    val uiState = _uiState.asStateFlow()


    fun fetchProductsList() {
        _uiState.update {
            MainFragmentUiState.Loading
        }

        viewModelScope.launch {
            try {
                val womenClothing = repo.getWomenClothingProducts()

                womenClothing?.let { list ->
                    _uiState.update {
                        MainFragmentUiState.Success(list)
                    }

                } ?: run {
                    _uiState.update {
                        MainFragmentUiState.Error(MainFragmentUiError.NoProducts)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    MainFragmentUiState.Error(MainFragmentUiError.GenericError)
                }
            }
        }

    }

    init {
       fetchProductsList()
    }

}