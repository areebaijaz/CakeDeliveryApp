package com.example.cakedeliveryapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakedeliveryapp.domain.usecase.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {


    fun saveEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCases()
        }
    }

}