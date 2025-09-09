package com.example.cakedeliveryapp.domain.manger

import kotlinx.coroutines.flow.Flow

interface UserEntry {

    suspend fun saveUserEntry()

    fun readUserEntry() : Flow<Boolean>
}