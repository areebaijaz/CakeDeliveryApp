package com.example.cakedeliveryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cakedeliveryapp.data.local.entity.CakeEntity

@Database(entities = [CakeEntity::class], version = 1, exportSchema = false)
abstract class CakeDatabase : RoomDatabase() {
    companion object{
        fun getInstance(context : Context) =
            Room.databaseBuilder(context,CakeDatabase::class.java,
            "cakes"
            ).build()
    }

    abstract fun getCakeDao() : CakeDao
}