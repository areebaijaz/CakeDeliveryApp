package com.example.cakedeliveryapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class DetailViewModel @Inject constructor(
    private val cakeDataUseCase: CakeDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

  private  val cakeId : Int =savedStateHandle.get<String>("cakeId")?.toIntOrNull() ?: -1

    var isLoading by mutableStateOf(true)
        private set

    private val _cake = MutableStateFlow<Cake?>(null)
    val cake :StateFlow<Cake?> = _cake

    init {

        viewModelScope.launch {
            if(cakeId != -1) {
                try {
                    _cake.value = cakeDataUseCase.getCakeById(cakeId)
                } catch (e: Exception) {
                    _cake.value = null

                }finally {
                    isLoading = false
                }
            } else {
                isLoading = false
            }

        }
    }


}