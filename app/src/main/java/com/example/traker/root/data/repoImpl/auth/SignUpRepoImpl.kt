package com.example.traker.root.data.repoImpl.auth

import android.util.Patterns
import com.example.traker.root.domain.repository.authRepo.SignUpRepo
import com.example.traker.root.domain.useCase.auth.Validaction

class SignUpRepoImpl : SignUpRepo {

    override fun name(name: String): Validaction {
       return if(name.isBlank()){
           Validaction(error = "Enter the name")
       }else{
           Validaction(success = true)
       }
    }

    override fun email(email: String): Validaction {
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

    override fun conformPassword(password: String, conformPassword: String): Validaction {
        return if(conformPassword.isBlank()){
            Validaction(error = "Enter the conform password")
        }else if(conformPassword!=password){
            Validaction(error = "Not match on conform password")
        }else{
            Validaction(success = true)
        }
    }

    override fun checkTermsBox(check: Boolean): Validaction {
        return if (!check) {
            Validaction(error = "Read accepted terms and condition")
        } else {
            Validaction(success = true)
        }
    }
}