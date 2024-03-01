package com.example.traker.root.di.database

import com.example.traker.root.data.repoImpl.database.RealmDatabaseRepoImpl
import com.example.traker.root.data.user.UserData
import com.example.traker.root.data.user.UserLocation
import com.example.traker.root.domain.repository.databaseRepo.RealmDatabaseRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmDatabase {

    @Provides
    @Singleton
    fun provideRealDatabase() : Realm{
        val config = RealmConfiguration.Builder(
            schema = setOf(UserLocation::class,UserData::class)
        ).compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideRealmDatabaseRepoImpl(realm: Realm) :RealmDatabaseRepo{
        return RealmDatabaseRepoImpl(realm=realm)
    }

}