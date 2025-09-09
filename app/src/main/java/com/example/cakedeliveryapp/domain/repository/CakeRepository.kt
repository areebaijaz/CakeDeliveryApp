package com.example.cakedeliveryapp.domain.repository

import com.example.cakedeliveryapp.domain.model.Cake
import kotlinx.coroutines.flow.Flow

interface CakeRepository {

    //Fetch data from retrofit
    suspend fun getCakes() : Result<List<Cake>>

    //Fetch saved data from DB
    fun getAllCake() : Flow<List<Cake>>

    suspend fun getCakeById(cakeId : Int) : Cake?

    suspend fun updateCakeId(cakeId : Int,qty : Int) : Cake?
}