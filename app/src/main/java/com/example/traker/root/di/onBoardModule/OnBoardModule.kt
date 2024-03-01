package com.example.traker.root.di.onBoardModule

import android.content.Context
import com.example.traker.root.data.repoImpl.onBoard.OnBoardRepoImpl
import com.example.traker.root.domain.repository.onBoardRepo.OnBoardRepo
import com.example.traker.root.domain.useCase.onBoardUseCase.ReadOnBoard
import com.example.traker.root.domain.useCase.onBoardUseCase.SaveOnBoard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardModule {

    @Provides
    @Singleton
    fun provideOnBoardRepoImpl(
        @ApplicationContext context: Context
    ): OnBoardRepo {
        return OnBoardRepoImpl(context=context)
    }

    @Provides
    @Singleton
    fun provideReadOnBoard(onBoardRepo: OnBoardRepo) = ReadOnBoard(onBoardRepo=onBoardRepo)

    @Provides
    @Singleton
    fun provideSaveOnBoard(onBoardRepo: OnBoardRepo) : SaveOnBoard{
        return SaveOnBoard(onBoardRepo=onBoardRepo)
    }


}