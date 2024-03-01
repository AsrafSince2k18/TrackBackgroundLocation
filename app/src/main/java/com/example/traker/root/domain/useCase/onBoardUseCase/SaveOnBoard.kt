package com.example.traker.root.domain.useCase.onBoardUseCase

import com.example.traker.root.domain.repository.onBoardRepo.OnBoardRepo

class SaveOnBoard(val onBoardRepo: OnBoardRepo) {
    suspend operator fun invoke()=onBoardRepo.saveScreen()

}