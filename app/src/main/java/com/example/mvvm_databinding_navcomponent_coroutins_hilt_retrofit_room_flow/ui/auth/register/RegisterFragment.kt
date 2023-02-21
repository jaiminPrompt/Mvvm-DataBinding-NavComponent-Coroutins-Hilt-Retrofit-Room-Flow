package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.register

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.server.ApiServices
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.FragmentRegisterBinding
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthRequest
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthResponse
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var apiServices: ApiServices

    private lateinit var authResponse: AuthResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        binding.btnRegister.setOnClickListener {
            if (validations()) {
                callRegisterApi()
            }
        }

        return binding.root
    }

    private fun callRegisterApi() {

        lifecycleScope.launch {
            val response = apiServices.register(
                authRequest = AuthRequest(
                    binding.edtEmailId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            )

            if (response.isSuccessful) {

                authResponse = response.body()!!
                Toast.makeText(
                    requireContext(),
                    "Register Successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(TAG, "callRegisterApi: ${authResponse.token}")
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                    authResponse.token.toString()
                )
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    requireContext(),
                    "" + response.errorBody()!!.string(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun validations(): Boolean {
        if (binding.edtEmailId.text.isNullOrEmpty()) {
            binding.edtEmailId.error = "Please Enter Email Id."
            binding.edtEmailId.requestFocus()
        } else if (!isValidEmail(binding.edtEmailId.text)) {
            binding.edtEmailId.error = "Please Enter Valid Email."
            binding.edtEmailId.requestFocus()
        } else if (binding.edtPassword.text!!.isEmpty()) {
            binding.edtPassword.error = "Please Enter Password"
            binding.edtPassword.requestFocus()
        } else if (binding.edtPassword.text!!.length < 6) {
            binding.edtPassword.error = "Enter Password length Should be Grater Then 6 Character"
            binding.edtPassword.requestFocus()
        } else {
            return true
        }
        return false
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target.toString())
            .matches()
    }

}