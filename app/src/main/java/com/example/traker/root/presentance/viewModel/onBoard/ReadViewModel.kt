package com.example.traker.root.presentance.viewModel.onBoard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traker.root.domain.useCase.onBoardUseCase.ReadOnBoard
import com.example.traker.root.presentance.rootScreen.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReadViewModel @Inject constructor(
    private val readOnBoard: ReadOnBoard
)  : ViewModel(){

    var screen by mutableStateOf(Screen.OnBoardScreen.route)
        private set
    var splashScreen by mutableStateOf(true)
        private set

    init {
        readOnBoard.invoke().onEach { check->
            screen = if(check){
                Screen.SignInScreen.route
            }else{
                Screen.OnBoardScreen.route
            }
            delay(300)
            splashScreen=false

        }.launchIn(viewModelScope)
    }

}