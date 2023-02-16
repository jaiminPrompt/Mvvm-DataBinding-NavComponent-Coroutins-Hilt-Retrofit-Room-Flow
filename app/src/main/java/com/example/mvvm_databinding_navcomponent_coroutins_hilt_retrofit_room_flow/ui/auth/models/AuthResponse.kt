package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("token")
    val token: String? = null

)
