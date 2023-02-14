package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var myData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set data in textview
        binding.test = myData

    }

}