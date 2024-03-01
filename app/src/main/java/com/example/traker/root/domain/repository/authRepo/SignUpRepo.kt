package com.example.traker.root.domain.repository.authRepo

import com.example.traker.root.domain.useCase.auth.Validaction

interface SignUpRepo {

    fun name(name:String) : Validaction

    fun email(email:String) : Validaction

    fun password(password : String) : Validaction

    fun conformPassword(password : String,
                        conformPassword : String):Validaction

    fun checkTermsBox (check : Boolean) : Validaction

}