package com.example.cakedeliveryapp.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val quantity : Int = 1
)