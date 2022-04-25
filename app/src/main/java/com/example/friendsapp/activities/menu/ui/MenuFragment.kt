package com.example.friendsapp.activities.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.friendsapp.R
import com.example.friendsapp.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddFriends.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_AddFriendsFragment)
        }

        binding.buttonMyFriends.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_MyFriendsFragment)
        }

        binding.buttonFriendreqs.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_FriendReqsFragment)
        }

        binding.buttonHelp.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_HelpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}