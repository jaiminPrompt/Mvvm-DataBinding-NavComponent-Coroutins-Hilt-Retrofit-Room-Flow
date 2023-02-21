package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDatabase
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.server.ApiServices
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.ActivityMainBinding
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.dao.UsersDao
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthResponse
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.UserResponse
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.Users
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}