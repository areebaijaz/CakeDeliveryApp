package com.example.cakedeliveryapp.domain.usecase.cake

import com.example.cakedeliveryapp.domain.model.Cake
import com.example.cakedeliveryapp.domain.repository.CakeRepository
import kotlinx.coroutines.flow.Flow

class GetAllCakesUseCase(
    private val cakeRepository: CakeRepository
) {

    operator fun invoke() : Flow<List<Cake>> {
     return   cakeRepository.getAllCake()
    }
}