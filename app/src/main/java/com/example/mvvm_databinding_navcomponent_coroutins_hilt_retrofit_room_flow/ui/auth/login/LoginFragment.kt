package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.R
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.server.ApiServices
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.FragmentLoginBinding
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthRequest
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthResponse
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.utils.Constants
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var apiServices: ApiServices

    private lateinit var authResponse: AuthResponse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get data from arguments
        val data = arguments?.getString("register_msg")
        Toast.makeText(requireContext(), "" + data, Toast.LENGTH_SHORT).show()
        Log.e(TAG, "onViewCreated: $data")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.btnLogin.setOnClickListener {
            if (validations()) {
                callLoginApi()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun callLoginApi() {
        lifecycleScope.launch {
            val response = apiServices.login(
                authRequest = AuthRequest(
                    binding.edtEmailId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            )

            if (response.isSuccessful) {
                authResponse = response.body()!!
                Toast.makeText(
                    requireContext(),
                    "Login Successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(TAG, "callRegisterApi: ${authResponse.token}")
                val action =
                    LoginFragmentDirections.actionLoginFragmentToDashboardFragment(authResponse.token.toString())
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
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}