package com.example.friendsapp.webapi.callbacks

import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import com.example.friendsapp.activities.menu.ui.MyFriendsFragment
import com.example.friendsapp.apiInterface
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.DeleteFriendsResponse
import com.example.friendsapp.webapi.ListFriendsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendsResponseCallback(private val fragment : MyFriendsFragment, private val layout : LinearLayout) : Callback<ListFriendsResponse> {
    override fun onResponse(call: Call<ListFriendsResponse>, response: Response<ListFriendsResponse>) {
        if (response.isSuccessful){
            response.body()?.users?.forEach {
                val lay = LinearLayout(fragment.context)
                lay.orientation = LinearLayout.HORIZONTAL
                lay.addView(generateFriendText(it))
                lay.addView(generateFriendButton(it))
                layout.addView(lay)
            }
        }
    }

    override fun onFailure(call: Call<ListFriendsResponse>, t: Throwable) {

    }
    private fun generateFriendText(friend: String) : TextView {
        val text = TextView(fragment.context)
        text.text = friend + "   "
        text.setTextColor(BLACK)
        text.textSize = 20F
        return text
    }

    private fun generateFriendButton(friend: String) : Button{
        val btn = Button(fragment.context)
        btn.text = "Delete"
        btn.isAllCaps = false
        btn.setBackgroundColor(RED)
        btn.setOnClickListener {
            apiInterface.deleteFriends(username,friend).enqueue(DeleteFriendRequestCallback(btn))
        }
        return btn
    }
}

class DeleteFriendRequestCallback(private val btn : Button) : Callback<DeleteFriendsResponse>{
    override fun onResponse(call: Call<DeleteFriendsResponse>, response: Response<DeleteFriendsResponse>) {
        if (response.isSuccessful) {
                btn.setBackgroundColor(Color.GREEN)
                println(response.body()?.message)
        }
    }

    override fun onFailure(call: Call<DeleteFriendsResponse>, t: Throwable) {

    }

}