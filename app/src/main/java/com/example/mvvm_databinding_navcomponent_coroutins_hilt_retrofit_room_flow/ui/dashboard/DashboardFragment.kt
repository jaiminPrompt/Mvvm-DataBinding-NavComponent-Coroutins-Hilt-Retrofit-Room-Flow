package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.R
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.FragmentDashboardBinding
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.utils.Constants.TAG

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data from arguments
        val data = arguments?.getString("login_msg")
        Toast.makeText(requireContext(), "" + data, Toast.LENGTH_SHORT).show()
        Log.e(TAG, "onViewCreated: $data")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        binding.txtDashboard.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}