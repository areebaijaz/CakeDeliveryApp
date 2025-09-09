package com.example.cakedeliveryapp.data.model

data class CakeResponseItem(
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val quantity: Int
)