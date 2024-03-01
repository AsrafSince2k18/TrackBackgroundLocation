package com.example.traker.root.di.authModule

import com.example.traker.root.data.repoImpl.auth.SignUpRepoImpl
import com.example.traker.root.domain.repository.authRepo.SignUpRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepoImpl() : SignUpRepo {
        return SignUpRepoImpl()
    }


}