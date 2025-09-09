package com.example.cakedeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cakes")
data class CakeEntity(
    val description: String,
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val quantity : Int

)
