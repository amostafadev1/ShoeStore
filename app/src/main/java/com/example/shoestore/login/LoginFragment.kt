package com.example.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoestore.databinding.FragmentLoginBinding

const val EMAIL = "email"
const val PASSWORD = "password"

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // To remove error off the field when tapped.
        addFieldFocusListeners()

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.registerButton.setOnClickListener {
            login()
        }
    }

    // Remove error when tapping the field
    private fun addFieldFocusListeners() {
        binding.emailEditText.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (focused)
                binding.emailInputLayout.error = null
        }
        binding.passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (focused)
                binding.passwordInputLayout.error = null
        }
    }

    // Navigate to the Welcome screen with either button, unless empty input.
    private fun login() {

        // Contains either email or password, or empty.
        val emptyInput: MutableList<String> = mutableListOf()

        val emailFieldContent = binding.emailEditText.text?.trim().toString()

        // Add which field is empty to emptyInput list.
        if (emailFieldContent.isEmpty())
            emptyInput.add(EMAIL)
        if (binding.passwordEditText.text?.trim().isNullOrEmpty())
            emptyInput.add(PASSWORD)

        // If no field is empty, navigate.
        if (emptyInput.isEmpty())
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(emailFieldContent)
            )
        else {
            val emptyPasswordIndex = emptyInput.indexOf(PASSWORD)
            // If password field is empty i.e. in the emptyInput list.
            if (emptyPasswordIndex > -1)
                binding.passwordInputLayout.error = "Please enter $PASSWORD"
            // If email field is empty i.e., either password is not inside the emptyInput list
            // or password is inside and has index of 1.
            if (emptyPasswordIndex != 0)
                binding.emailInputLayout.error = "Please enter $EMAIL"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}