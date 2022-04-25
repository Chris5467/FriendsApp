package com.example.friendsapp.webapi.callbacks

import com.example.friendsapp.apiInterface
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.LocationRequestBody
import com.example.friendsapp.webapi.LocationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateLocationResponseCallback(private val long: Double, private val lat: Double) : Callback<LocationResponse>{
    override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
        if (response.isSuccessful){
            apiInterface.updateLocation(LocationRequestBody(username, long, lat))
        }
    }

    override fun onFailure(call: Call<LocationResponse>, t: Throwable) {

    }
}