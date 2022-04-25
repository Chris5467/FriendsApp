package com.example.friendsapp.webapi.callbacks

import android.graphics.Color
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.friendsapp.activities.menu.ui.AddFriendsFragment
import com.example.friendsapp.apiInterface
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.FindNewFriendsResponse
import com.example.friendsapp.webapi.SendReqRequest
import com.example.friendsapp.webapi.SendReqResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendsResponseCallback(private val fragment : AddFriendsFragment, private val layout : LinearLayout) : Callback<FindNewFriendsResponse> {
    override fun onResponse(call: Call<FindNewFriendsResponse>, response: Response<FindNewFriendsResponse>) {
        if (response.isSuccessful){
            response.body()?.users?.forEach {
                val lay = LinearLayout(fragment.context)
                lay.orientation = LinearLayout.HORIZONTAL
                lay.addView(generateFriendText(it))
                lay.addView(generateAddButton(it))
                layout.addView(lay)
            }
        }
    }

    override fun onFailure(call: Call<FindNewFriendsResponse>, t: Throwable) {

    }

    private fun generateFriendText(friendRequest: String) : TextView {
        val text = TextView(fragment.context)
        text.text = friendRequest
        text.setTextColor(Color.BLACK)
        text.textSize = 16F
        return text
    }

    private fun generateAddButton(receiver_username: String) : Button{
        val btn = Button(fragment.context)
        btn.text = "Send Request"
        btn.isAllCaps = false
        btn.setOnClickListener {
            apiInterface.sendFriendReq(SendReqRequest(username,receiver_username)).enqueue(
                SendReqRequestCallback(btn)
            )
        }
        return btn
    }
}

class SendReqRequestCallback(private val btn : Button) : Callback<SendReqResponse>{
    override fun onResponse(call: Call<SendReqResponse>, response: Response<SendReqResponse>) {
        if (response.isSuccessful) {
            if (response.body()?.ok == true) {
                btn.setBackgroundColor(Color.GREEN)
            }
            else {
                btn.setBackgroundColor(Color.YELLOW)
            }
        }
    }

    override fun onFailure(call: Call<SendReqResponse>, t: Throwable) {

    }

}