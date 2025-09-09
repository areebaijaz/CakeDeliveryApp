package com.example.cakedeliveryapp.domain.model

data class Cake(
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val quantity : Int
)
