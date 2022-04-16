package com.example.friendsapp.webapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

        @GET("/login")
        fun login(@Query("username")username: String, @Query("password")password: String) : Call<LoginResponse>

        @POST("/register")
        fun addUser(@Body userData: RegisterRequestBody) : Call<RegisterResponse>

        companion object {
                var BASE_URL = "http://82.38.253.189:8080/"

                fun create() : ApiInterface {
                        val retrofit = Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .baseUrl(BASE_URL)
                                .build()
                        return retrofit.create(ApiInterface::class.java)
                }
        }
}

data class LoginResponse(val ok: Boolean, val message: String, val username: String)

data class RegisterResponse(val ok:Boolean, val message: String)

data class RegisterRequestBody(val email: String, val username: String, val password: String)