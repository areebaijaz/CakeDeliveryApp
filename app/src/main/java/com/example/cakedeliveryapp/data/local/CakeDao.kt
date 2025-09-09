package com.example.cakedeliveryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cakedeliveryapp.data.local.entity.CakeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CakeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cakeEntity: List<CakeEntity>)

    @Query("SELECT * FROM cakes WHERE id = :cakeId LIMIT 1")
    suspend fun getCakeById(cakeId : Int) : CakeEntity?

    @Query("UPDATE cakes SET quantity =  :qty WHERE id =:cakeId ")
    suspend fun updateCakeQuantity(cakeId : Int,qty : Int) : Int

    @Query("SELECT * FROM cakes")
    fun getAllCakes() : Flow<List<CakeEntity>>

}