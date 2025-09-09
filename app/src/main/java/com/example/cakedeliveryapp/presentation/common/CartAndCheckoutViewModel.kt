package com.example.cakedeliveryapp.presentation.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakedeliveryapp.domain.model.Cake
import com.example.cakedeliveryapp.domain.usecase.cake.CakeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartAndCheckoutViewModel @Inject constructor(
    private val cakeDataUseCase: CakeDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cakeId : Int = savedStateHandle.get<String>("cakeId")?.toIntOrNull() ?: -1

    private val _cake = MutableStateFlow<Cake?>(null)
    val cake :StateFlow<Cake?> = _cake


    init {
        viewModelScope.launch {
            _cake.value = cakeDataUseCase.getCakeById(cakeId)
        }

    }

     fun updateWithCakeId(newQuantity : Int) {
        viewModelScope.launch {
            _cake.value =  cakeDataUseCase.updateCakeQuantity(cakeId,newQuantity)


        }
    }




}