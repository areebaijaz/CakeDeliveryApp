package com.example.cakedeliveryapp.data

import com.example.cakedeliveryapp.data.local.entity.CakeEntity
import com.example.cakedeliveryapp.data.model.CakeDto
import com.example.cakedeliveryapp.domain.model.Cake

// Retrofit response to Domain Model
fun CakeDto.toDomain(id : String) : Cake{
    return Cake(
        id = id ?: "",
        name = name ?: "",
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        price = price ?: 0,
    )
}

// Domain Model to Room Entity
fun Cake.toEntity() : CakeEntity {
    return CakeEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        price = price,

    )
}

//Room entity to model
fun CakeEntity.toDomain() : Cake {
    return Cake(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        price = price,

    )

}