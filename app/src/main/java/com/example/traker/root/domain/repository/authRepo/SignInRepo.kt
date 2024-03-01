package com.example.traker.root.domain.repository.authRepo

import com.example.traker.root.domain.useCase.auth.Validaction

interface SignInRepo {

    fun userEmail(email:String) :Validaction
    fun password(password:String) :Validaction

}