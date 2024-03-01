package com.example.traker.root.di.location

import android.content.Context
import com.example.traker.root.data.repoImpl.location.LocationRepoImpl
import com.example.traker.root.domain.repository.location.LocationRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationRepoImpl(
        @ApplicationContext context: Context,
        fused : FusedLocationProviderClient
    ) : LocationRepo{
        return LocationRepoImpl(context = context, client = fused)
        
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}