package com.example.cakedeliveryapp.domain.usecase.cake

import com.example.cakedeliveryapp.domain.model.Cake
import com.example.cakedeliveryapp.domain.repository.CakeRepository

class UpdateCakeQuantity(
    private val cakeRepository: CakeRepository
) {

    suspend operator fun invoke(cakeId : Int ,qty : Int) : Cake? {
     return  cakeRepository.updateCakeId(cakeId, qty)
    }
}