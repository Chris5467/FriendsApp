package com.example.friendsapp.activities.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.friendsapp.R
import com.example.friendsapp.webapi.callbacks.LoginResponseCallback
import com.example.friendsapp.apiInterface
import com.example.friendsapp.databinding.FragmentLoginBinding
import com.example.friendsapp.util.isValidPassword
import com.example.friendsapp.util.isValidUsername

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButtonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }
        binding.loginButtonLogin.setOnClickListener {
            if (isValidUsername(binding.loginInputUsername.text.toString()) && isValidPassword(binding.loginInputPassword.text.toString())) {
                apiInterface.login(binding.loginInputUsername.text.toString(),
                    binding.loginInputPassword.text.toString()).enqueue(LoginResponseCallback(this))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}