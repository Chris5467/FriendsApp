package com.example.friendsapp.util

import android.text.TextUtils
import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {
    return if (TextUtils.isEmpty(email)) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

fun isValidUsername(username: String): Boolean {
    return if (TextUtils.isEmpty(username)) {
        false
    } else {
        val usernameREGEX = Pattern.compile(
            "^(?=[a-zA-Z0-9._]{3,20}\$)(?!.*[_.]{2})[^_.].*[^_.]\$"
        )
        return usernameREGEX.matcher(username).matches()
    }
}

fun isValidPassword(password: String): Boolean {
    return if (TextUtils.isEmpty(password)) {
        false
    } else {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=!])" +   //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        )
        return passwordREGEX.matcher(password).matches()
    }
}