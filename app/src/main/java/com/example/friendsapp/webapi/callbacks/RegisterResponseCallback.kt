package com.example.friendsapp.webapi.callbacks

import androidx.navigation.fragment.findNavController
import com.example.friendsapp.R
import com.example.friendsapp.activities.login.ui.RegisterFragment
import com.example.friendsapp.webapi.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterResponseCallback(private val registerFragment: RegisterFragment) : Callback<RegisterResponse> {
    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
        if (response.isSuccessful) {
            registerFragment.findNavController()
                .navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }
    }

    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
        t.printStackTrace()
    }
}
