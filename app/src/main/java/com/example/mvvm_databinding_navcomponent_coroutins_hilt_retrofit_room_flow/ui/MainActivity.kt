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

    @Inject
    lateinit var database: UserDatabase

    @Inject
    lateinit var usersDao: UsersDao

    @Inject
    lateinit var apiServices: ApiServices

    private lateinit var authResponse: AuthResponse
    private lateinit var userResponse: UserResponse
    private lateinit var usersList: List<Users>
    val data: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authResponse = AuthResponse()

        getDataApiCall()

    }

    private fun getDataApiCall() {

        lifecycleScope.launch {
            userResponse = apiServices.getData().body()!!
            usersList = userResponse.data as List<Users>
            insertData(usersList)

        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            getDataFromDatabase()
            setData()
        }

    }

    private fun setData() {
        for (i in usersList.indices) {
            //set data into textview
            binding.test = data.append(usersList[i].email + "\n").toString()
        }
    }

    private suspend fun getDataFromDatabase() {
        usersList = database.usersDao().getData()
    }

    private suspend fun insertData(usersList: List<Users>) {
        database.usersDao().insert(usersList)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            //delete all data from user table
            database.usersDao().deleteAll()
        }
    }

}