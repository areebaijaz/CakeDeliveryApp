package com.example.cakedeliveryapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakedeliveryapp.domain.usecase.cake.CakeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cakeDataUseCase: CakeDataUseCase
) : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var state = cakeDataUseCase.getAllCakesUseCase().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            val result =cakeDataUseCase.getCakesUseCases()
            result.onSuccess { list ->
                Log.d("HomeViewModel", "Cakes loaded successfully, size=${list.size}")
            }

            result.onFailure { exception ->
                Log.e("HomeViewModel", "API failed, using cached data", exception)
            }

            isLoading = false


        }
    }

}