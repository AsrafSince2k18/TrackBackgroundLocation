package com.example.traker.root.presentance.auth.stateEvent.signUp

sealed class SignUpEvent {

    data class Name(val name : String) : SignUpEvent()
    data class Email(val email : String) : SignUpEvent()
    data class Password(val password : String) : SignUpEvent()
    data class ConformPassword(val conformPassword : String) : SignUpEvent()
    data class CheckBoxTerms(val check : Boolean) : SignUpEvent()
    object SignInOrSignUp : SignUpEvent()

}