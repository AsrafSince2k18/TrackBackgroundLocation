package com.example.traker.root.presentance.auth.stateEvent.signUp

data class SignUpState(

    val name : String ="",
    val nameError : String?=null,

    val email : String="",
    val emailError : String?=null,

    val password : String="",
    val passwordError : String?=null,

    val conformPassword : String="",
    val conformPasswordError : String?=null,

    val checkBoxTerms : Boolean =false,
    val checkBoxError : String?=null,

)
