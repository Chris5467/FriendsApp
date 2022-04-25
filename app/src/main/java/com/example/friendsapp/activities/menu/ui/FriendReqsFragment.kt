package com.example.friendsapp.activities.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.friendsapp.R
import com.example.friendsapp.apiInterface
import com.example.friendsapp.databinding.FragmentFriendreqsBinding
import com.example.friendsapp.storage.username
import com.example.friendsapp.webapi.callbacks.FriendReqsResponseCallback

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FriendReqsFragment : Fragment() {

    private var _binding: FragmentFriendreqsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFriendreqsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonReturn4.setOnClickListener {
            findNavController().navigate(R.id.action_FriendReqsFragment_to_MenuFragment)
        }
        apiInterface.listFriendReqs(username).enqueue(FriendReqsResponseCallback(this,binding.linearFriendreqs))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}