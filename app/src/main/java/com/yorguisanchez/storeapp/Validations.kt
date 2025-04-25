package com.yorguisanchez.storeapp

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns

fun ValidateEmail(email: String): Pair<Boolean, String>{
    return when{
        email.isEmpty() -> Pair(false, "El correo es requerido")
        !email.endsWith("@unab.edu.co") -> Pair(false, "Solo correos institucionales")
        Patterns.EMAIL_ADDRESS.matcher(email).matches()-> Pair(false, "Solo correos validos")
        else -> {
            Pair(true, "")
        }
    }

}

fun ValidatePassword(password:String): Pair <Boolean, String>{
    return when{
        password.isEmpty() -> Pair(false, "La contraseña es requeroida")
        password.length < 8 -> Pair(false, "La contraseña debe tener minimo 8 caracteres")
        else -> {
            Pair(true, "")
        }
    }

}