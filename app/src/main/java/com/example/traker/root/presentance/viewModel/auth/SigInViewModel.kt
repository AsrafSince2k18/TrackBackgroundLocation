package com.example.traker.root.presentance.viewModel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traker.root.domain.repository.authRepo.SignInRepo
import com.example.traker.root.presentance.auth.stateEvent.signIn.SignInEvent
import com.example.traker.root.presentance.auth.stateEvent.signIn.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val signInRepo: SignInRepo,
) : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()


    fun onEvent(event:SignInEvent){
        when(event){
            is SignInEvent.Email -> {
                _signInState.update {
                    it.copy(email = event.email)
                }
            }
            is SignInEvent.Password -> {
                _signInState.update {
                    it.copy(password = event.password)
                }
            }
            is SignInEvent.SignIn -> {
                    signInScreen()

            }
        }
    }

    private fun signInScreen() {
        viewModelScope.launch {
            val email = signInRepo.userEmail(signInState.value.email)
            val password = signInRepo.password(signInState.value.password)

            val check = listOf(email, password).any { !it.success }

            if (check) {
                _signInState.update {
                    it.copy(
                        emailError = email.error,
                        passwordError = password.error
                    )
                }
            } else {
                _signInState.update {
                    it.copy(
                        emailError = null,
                        passwordError = null
                    )
                }
                Log.d("myTag", "signInScreenUP")

            }
        }
    }



}