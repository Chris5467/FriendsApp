package com.example.friendsapp.webapi.callbacks

import com.example.friendsapp.apiInterface
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.FriendsLocationsResponse
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendsLocationResponseCallback(private val map: GoogleMap) : Callback<FriendsLocationsResponse>{
    override fun onResponse(
        call: Call<FriendsLocationsResponse>,
        response: Response<FriendsLocationsResponse>
    ) {
        if (response.isSuccessful) {
            if (response.body()?.ok == true) {
                apiInterface.friendLocations(username)
                response.body()?.friendsLocations?.forEach {
                    map.addMarker(
                        MarkerOptions()
                            .title(it.username)
                            .position(LatLng(it.lat.toDouble(), it.long.toDouble()))
                            .snippet("${it.username} is here!"))

                }
            }
        }
    }

    override fun onFailure(call: Call<FriendsLocationsResponse>, t: Throwable) {

    }
}