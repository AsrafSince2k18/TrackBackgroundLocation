package com.example.traker.root.presentance.auth.stateEvent.signIn


data class SignInState(

    val email :String="",
    val emailError :String?=null,

    val password :String="",
    val passwordError :String?=null,


)
