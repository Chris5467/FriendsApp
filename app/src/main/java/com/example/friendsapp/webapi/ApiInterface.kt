package com.example.friendsapp.webapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

        // GET function for logging in
        @GET("/user/login")
        fun login(@Query("username")username: String, @Query("password")password: String) : Call<LoginResponse>

        // POST function for registering an account
        @POST("/user/register")
        fun addUser(@Body userData: RegisterRequestBody) : Call<RegisterResponse>

        // GET function for listing added friends
        @GET("/user/friends")
        fun listFriends(@Query("username")username: String) : Call<ListFriendsResponse>

        // DELETE function to delete friends
        @DELETE("/user/friends")
        fun deleteFriends(@Query("username")username: String, @Query("deletedFriend")deletedFriend: String) : Call<DeleteFriendsResponse>

        // GET function for receiving friends locations
        @GET("/user/friends/locations")
        fun friendLocations(@Query("username")username: String) : Call<FriendsLocationsResponse>

        // GET function for listing friend requests
        @GET("/user/friendreqs")
        fun listFriendReqs(@Query("username")username: String) : Call<FriendReqResponse>

        // DELETE function for deleting friend requests
        @DELETE("/user/friendreqs")
        fun deleteFriendReqs(@Query("username")username: String, @Query("deletedRequest")deletedRequest: String) : Call<DeleteReqResponse>

        // POST function to add a friend
        @POST("/user/friends")
        fun addFriend(@Body friendData: AddFriendRequestBody) : Call<AddFriendResponse>

        // POST function for updating users location
        @POST("/user/location")
        fun updateLocation(@Body locationData: LocationRequestBody) : Call <LocationResponse>

        // GET function for searching new friends
        @GET("/findfriends")
        fun searchNewFriends(@Query("username")username: String): Call<FindNewFriendsResponse>

        // POST function for sending friend requests
        @POST("/user/friendreqs")
        fun sendFriendReq(@Body reqData: SendReqRequest) : Call<SendReqResponse>

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

data class ListFriendsResponse(val ok: Boolean, val message: String, val users: List<String>)

data class AddFriendResponse(val ok: Boolean, val message: String)

data class AddFriendRequestBody(val username: String, val friend_username: String)

data class DeleteFriendsResponse(val ok: Boolean, val message: String)

data class FriendsLocationsResponse(val ok: Boolean, val message: String, val friendsLocations: List<UserLocation>)

data class UserLocation(val username: String, val long: Float, val lat: Float)

data class FriendReqResponse(val ok: Boolean, val message: String, val friendRequests: List<String>)

data class SendReqResponse(val ok: Boolean, val message: String)

data class SendReqRequest(val sender_username: String, val receiver_username: String)

data class DeleteReqResponse(val ok: Boolean, val message: String)

data class LocationRequestBody(val username: String, val long: Double, val lat: Double)

data class LocationResponse(val ok:Boolean, val message: String)

data class FindNewFriendsResponse(val ok: Boolean, val message: String, val users: List<String>)