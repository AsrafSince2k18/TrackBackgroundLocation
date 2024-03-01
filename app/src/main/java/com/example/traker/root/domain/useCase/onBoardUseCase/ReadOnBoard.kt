package com.example.traker.root.domain.useCase.onBoardUseCase

import com.example.traker.root.domain.repository.onBoardRepo.OnBoardRepo

class ReadOnBoard (val onBoardRepo: OnBoardRepo){
    operator fun invoke()=onBoardRepo.readScreen()
}