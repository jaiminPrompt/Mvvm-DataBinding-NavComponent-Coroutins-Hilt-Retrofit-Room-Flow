package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.test = "Hello Jaimin..!"

    }
}