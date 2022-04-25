package com.example.friendsapp.webapi.callbacks

import android.graphics.Color
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.friendsapp.activities.menu.ui.FriendReqsFragment
import com.example.friendsapp.apiInterface
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.AddFriendRequestBody
import com.example.friendsapp.webapi.AddFriendResponse
import com.example.friendsapp.webapi.DeleteReqResponse
import com.example.friendsapp.webapi.FriendReqResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendReqsResponseCallback(private val fragment : FriendReqsFragment, private val layout : LinearLayout) : Callback<FriendReqResponse> {
    override fun onResponse(call: Call<FriendReqResponse>, response: Response<FriendReqResponse>) {
        if (response.isSuccessful){
            response.body()?.friendRequests?.forEach {
                val lay = LinearLayout(fragment.context)
                lay.orientation = LinearLayout.HORIZONTAL
                lay.addView(generateRequestText(it))
                lay.addView(generateAddButton(it))
                lay.addView(generateDenyButton(it))
                layout.addView(lay)
            }
        }
    }

    override fun onFailure(call: Call<FriendReqResponse>, t: Throwable) {

    }

    private fun generateRequestText(friendRequest: String) : TextView {
        val text = TextView(fragment.context)
        text.text = friendRequest
        text.setTextColor(Color.BLACK)
        text.textSize = 32F
        return text
    }

    private fun generateAddButton(friend: String) : Button {
        val btn = Button(fragment.context)
        btn.text = "Add"
        btn.setOnClickListener {
            apiInterface.addFriend(AddFriendRequestBody(username, friend)).enqueue(
                AcceptReqRequestCallback(btn)
            )
        }
        return btn
    }

    private fun generateDenyButton(friend: String) : Button {
        val btn = Button(fragment.context)
        btn.text = "Deny"
        btn.setOnClickListener {
            apiInterface.deleteFriendReqs(friend, username).enqueue(DenyReqRequestCallback(btn))
        }
        return btn
    }
}

class AcceptReqRequestCallback(private val btn: Button) : Callback<AddFriendResponse> {
    override fun onResponse(call: Call<AddFriendResponse>, response: Response<AddFriendResponse>) {
        if (response.isSuccessful){
            if (response.body()?.ok == true) {
                btn.setBackgroundColor(Color.GREEN)
            }
            else {
                println(response.body()?.message)
                btn.setBackgroundColor(Color.YELLOW)
            }
        }
    }

    override fun onFailure(call: Call<AddFriendResponse>, t: Throwable) {

    }
}

class DenyReqRequestCallback(private val btn: Button) : Callback<DeleteReqResponse> {
    override fun onResponse(call: Call<DeleteReqResponse>, response: Response<DeleteReqResponse>) {
        if (response.isSuccessful) {
            if (response.body()?.ok == true) {
                btn.setBackgroundColor(Color.GREEN)
            }
            else {
                btn.setBackgroundColor(Color.YELLOW)
            }
        }
    }
    override fun onFailure(call: Call<DeleteReqResponse>, t: Throwable) {

    }
}

