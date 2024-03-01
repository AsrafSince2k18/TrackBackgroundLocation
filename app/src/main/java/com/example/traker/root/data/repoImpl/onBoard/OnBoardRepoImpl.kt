package com.example.traker.root.data.repoImpl.onBoard

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.traker.root.domain.repository.onBoardRepo.OnBoardRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnBoardRepoImpl @Inject constructor(
    private val context: Context
) : OnBoardRepo{

    override fun readScreen(): Flow<Boolean> {
        return context.dataStore.data.map {preference->
            preference[Preference.preferencesKey]?:false
        }
    }

    override suspend fun saveScreen() {
        context.dataStore.edit {preference->
            preference[Preference.preferencesKey] = true
        }
    }
}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="read")
object Preference{
    val preferencesKey = booleanPreferencesKey(name="save")
}