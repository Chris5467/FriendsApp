package com.example.friendsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.friendsapp.webapi.ApiInterface
import com.example.friendsapp.activities.login.LoginActivity


val apiInterface = ApiInterface.create()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
