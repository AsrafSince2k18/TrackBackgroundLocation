package com.example.traker.root.domain.repository.onBoardRepo

import kotlinx.coroutines.flow.Flow

interface OnBoardRepo {

    fun readScreen() : Flow<Boolean>

    suspend fun saveScreen()

}