package com.example.cakedeliveryapp.domain.usecase.cake

import com.example.cakedeliveryapp.domain.repository.CakeRepository

class GetCakesUseCase(
  private val cakeRepository: CakeRepository
) {

    suspend operator fun invoke() = cakeRepository.getCakes()
}