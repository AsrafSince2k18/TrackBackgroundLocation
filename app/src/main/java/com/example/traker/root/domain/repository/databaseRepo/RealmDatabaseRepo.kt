package com.example.traker.root.domain.repository.databaseRepo

import com.example.traker.root.data.user.UserLocation
import kotlinx.coroutines.flow.Flow

interface RealmDatabaseRepo {

    suspend fun insertLocation(userLocation: UserLocation)

    fun getAllData() : Flow<List<UserLocation>>

}