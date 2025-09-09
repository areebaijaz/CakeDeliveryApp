package com.example.cakedeliveryapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cakedeliveryapp.domain.usecase.appEntry.AppEntryUseCases
import com.example.cakedeliveryapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntryUseCases().onEach {shouldStartFromHome ->
            if(shouldStartFromHome){
                startDestination = Route.CakeNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(200)
            splashCondition = false

        }.launchIn(viewModelScope)


    }
}