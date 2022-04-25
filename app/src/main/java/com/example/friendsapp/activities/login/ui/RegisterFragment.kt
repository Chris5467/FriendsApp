package com.example.friendsapp.activities.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.friendsapp.R
import com.example.friendsapp.webapi.RegisterRequestBody
import com.example.friendsapp.webapi.callbacks.RegisterResponseCallback
import com.example.friendsapp.apiInterface
import com.example.friendsapp.databinding.FragmentRegisterBinding
import com.example.friendsapp.util.isValidEmail
import com.example.friendsapp.util.isValidPassword
import com.example.friendsapp.util.isValidUsername


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButtonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }
        binding.registerButtonLogin.setOnClickListener {
            if (isValidEmail(binding.registerInputEmail.text.toString())
                && isValidUsername(binding.registerInputUsername.text.toString())
                && isValidPassword(binding.registerInputPassword.text.toString())
            ) {
                println("Valid data")
                val body = RegisterRequestBody(binding.registerInputEmail.text.toString(),
                    binding.registerInputUsername.text.toString(),
                    binding.registerInputPassword.text.toString())
                apiInterface.addUser(body).enqueue(RegisterResponseCallback(this))
            } else {
                println("Invalid data")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}