package com.example.traker.root.presentance.viewModel.onBoard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traker.root.domain.useCase.onBoardUseCase.SaveOnBoard
import com.example.traker.root.presentance.onBoard.stateEvent.OnBoardEventSave
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val saveOnBoard: SaveOnBoard
)  : ViewModel(){
    fun onEvent(event: OnBoardEventSave){
        when(event){
            OnBoardEventSave.SignIn -> {
                saveScreen()
            }
        }
    }

  private fun saveScreen(){
        viewModelScope.launch {
            saveOnBoard.invoke()
        }
    }

}