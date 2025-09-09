package com.example.cakedeliveryapp.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.cakedeliveryapp.domain.manger.UserEntry
import com.example.cakedeliveryapp.util.Constants
import com.example.cakedeliveryapp.util.Constants.APP_ENTRY
import com.example.cakedeliveryapp.util.Constants.USER_ENTRY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserEntryImpl(private val context: Context) : UserEntry {

    override suspend fun saveUserEntry() {
        context.dataStore.edit {set ->
            set[PreferenceKey.APP_ENTRY] = true
        }


    }

    override fun readUserEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKey.APP_ENTRY] ?: false



        }

    }
}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_ENTRY)

private object PreferenceKey{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}