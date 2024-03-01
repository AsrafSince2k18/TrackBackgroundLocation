package com.example.traker.root.di.authModule

import com.example.traker.root.data.repoImpl.auth.SignInRepoImpl
import com.example.traker.root.domain.repository.authRepo.SignInRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignInModule {

    @Provides
    @Singleton
    fun provideSignInRepoImpl() : SignInRepo{
        return SignInRepoImpl()
    }

}