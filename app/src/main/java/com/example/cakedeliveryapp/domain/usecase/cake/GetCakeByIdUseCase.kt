package com.example.cakedeliveryapp.domain.usecase.cake

import com.example.cakedeliveryapp.domain.repository.CakeRepository

class GetCakeByIdUseCase (
    private val cakeRepository: CakeRepository
) {

    suspend operator fun invoke(cakeId : Int) =
        cakeRepository.getCakeById(cakeId)
}