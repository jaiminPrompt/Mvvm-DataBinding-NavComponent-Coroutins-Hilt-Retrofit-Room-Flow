package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.User
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDao
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDatabase
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var myData: String

    @Inject
    lateinit var database: UserDatabase

    @Inject
    lateinit var userDao: UserDao

    lateinit var userList: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        insertData()
        getDataFromDatabase()
        setData()

    }

    private fun setData() {
        for (i in userList.indices) {
            //set data into textview
            binding.test = userList[i].name
        }
    }

    private fun getDataFromDatabase() {
        userList = database.userDao().getData()
    }

    private fun insertData() {
        database.userDao().insert(User(1, "jaimin", 1234567890))
    }

    override fun onDestroy() {
        super.onDestroy()
        //delete all data from user table
        database.userDao().deleteAll()
    }

}