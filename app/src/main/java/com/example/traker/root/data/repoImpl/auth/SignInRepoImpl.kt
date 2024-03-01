package com.example.traker.root.data.repoImpl.auth

import android.util.Patterns
import com.example.traker.root.domain.repository.authRepo.SignInRepo
import com.example.traker.root.domain.useCase.auth.Validaction

class SignInRepoImpl : SignInRepo {
    override fun userEmail(email: String): Validaction {
        return if(email.isBlank()){
            Validaction(error = "Enter the email")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Validaction(error = "Enter valid email")
        }else{
            Validaction(success = true)
        }
    }

    override fun password(password: String): Validaction {
        return if(password.isBlank()){
            Validaction(error = "Enter the password")
        }else if(!password.any { it.isUpperCase() } && !password.any{it.isDigit()}){
            Validaction(error = "Enter the minimum one uppercase and one number")
        }else{
            Validaction(success = true)
        }
    }
}