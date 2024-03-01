package com.example.traker.root.data.repoImpl.database

import com.example.traker.root.data.user.UserLocation
import com.example.traker.root.domain.repository.databaseRepo.RealmDatabaseRepo
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmDatabaseRepoImpl(private val realm: Realm) : RealmDatabaseRepo {
    override suspend fun insertLocation(userLocation: UserLocation) {
        realm.write {
            copyToRealm(userLocation)
        }
    }

    override fun getAllData(): Flow<List<UserLocation>> {
        return realm.query<UserLocation>().asFlow().map { it.list.toList() }
    }
}