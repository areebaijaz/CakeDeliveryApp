package com.example.cakedeliveryapp.domain.usecase.appEntry

import com.example.cakedeliveryapp.domain.manger.UserEntry

class SaveAppEntryUseCase (
 private val userEntry: UserEntry
) {

    suspend operator fun invoke() = userEntry.saveUserEntry()
}