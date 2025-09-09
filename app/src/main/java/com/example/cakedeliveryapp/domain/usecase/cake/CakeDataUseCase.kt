package com.example.cakedeliveryapp.domain.usecase.cake

data class CakeDataUseCase(
    val getCakesUseCases: GetCakesUseCase,
    val getAllCakesUseCase: GetAllCakesUseCase,
    val getCakeById: GetCakeByIdUseCase,
    val updateCakeQuantity: UpdateCakeQuantity
)
