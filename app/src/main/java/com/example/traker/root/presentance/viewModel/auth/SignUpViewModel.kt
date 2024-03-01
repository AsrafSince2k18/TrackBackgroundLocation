package com.example.traker.root.presentance.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traker.root.domain.repository.authRepo.SignUpRepo
import com.example.traker.root.presentance.auth.stateEvent.signUp.SignUpEvent
import com.example.traker.root.presentance.auth.stateEvent.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: SignUpRepo,
) : ViewModel() {

    private val _authState = MutableStateFlow(SignUpState())
    val authState = _authState.asStateFlow()



    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.Name -> {
                _authState.update {
                    it.copy(name = event.name)
                }
            }

            is SignUpEvent.Email -> {
                _authState.update {
                    it.copy(email = event.email)
                }
            }

            is SignUpEvent.Password -> {
                _authState.update {
                    it.copy(password = event.password)
                }
            }

            is SignUpEvent.ConformPassword -> {
                _authState.update {
                    it.copy(conformPassword = event.conformPassword)
                }
            }

            is SignUpEvent.CheckBoxTerms -> {
                _authState.update {
                    it.copy(checkBoxTerms = event.check)
                }
            }

            is SignUpEvent.SignInOrSignUp -> {
                signInOrSignup()
            }


        }
    }

    private fun signInOrSignup() {
        viewModelScope.launch {
            val name = authRepo.name(authState.value.name)
            val email = authRepo.email(authState.value.email)
            val password = authRepo.password(authState.value.password)
            val conformPassword =
                authRepo.conformPassword(authState.value.password, authState.value.conformPassword)
            val checkBox = authRepo.checkTermsBox(authState.value.checkBoxTerms)
            val check = listOf(email, password, conformPassword, checkBox).any { !it.success }

            if (check) {
                _authState.update {
                    it.copy(
                        nameError = name.error,
                        emailError = email.error,
                        passwordError = password.error,
                        conformPasswordError = conformPassword.error,
                        checkBoxError = checkBox.error
                    )
                }
            } else {
                _authState.update {
                    it.copy(
                        nameError = null,
                        emailError = null,
                        passwordError = null,
                        conformPasswordError = null,
                        checkBoxError = null
                    )
                }

            }

        }
    }

}