package com.example.traker.root.presentance.auth.stateEvent.signIn

sealed class SignInEvent {

    data class Email(val email : String) : SignInEvent()
    data class Password(val password : String) : SignInEvent()
    object SignIn : SignInEvent()

}