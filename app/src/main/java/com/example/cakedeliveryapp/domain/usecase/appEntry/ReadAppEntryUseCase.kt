package com.example.cakedeliveryapp.domain.usecase.appEntry

import com.example.cakedeliveryapp.domain.manger.UserEntry
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val userEntry: UserEntry
) {

    operator fun invoke () : Flow<Boolean>{
        return userEntry.readUserEntry()
    }
}