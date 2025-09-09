package com.example.cakedeliveryapp.data

import com.example.cakedeliveryapp.data.local.entity.CakeEntity
import com.example.cakedeliveryapp.data.model.CakeResponseItem
import com.example.cakedeliveryapp.domain.model.Cake

// Retrofit response to Domain Model
fun CakeResponseItem.toDomain() : Cake{
    return Cake(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        price = price,
        quantity = quantity
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
        quantity = quantity
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
        quantity = quantity
    )

}