package com.example.friendsapp.webapi

import android.content.Intent
import com.example.friendsapp.activities.login.LoginFragment
import com.example.friendsapp.activities.map.MapsActivityCurrentPlace
import com.example.friendsapp.storage.username
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginResponseCallback(private val loginFragment: LoginFragment): Callback<LoginResponse> {
    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if (response.isSuccessful){
            username = response.body()?.username.toString()
            val intent = Intent(loginFragment.context, MapsActivityCurrentPlace::class.java)
            loginFragment.startActivity(intent)
        }
    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        t.printStackTrace()
    }
}