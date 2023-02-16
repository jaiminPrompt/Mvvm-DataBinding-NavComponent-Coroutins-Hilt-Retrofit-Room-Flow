package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.server

import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthRequest
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.AuthResponse
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @POST("/api/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/login")
    suspend fun login(@Body authRequest: AuthRequest): Response<AuthResponse>

    @GET("/api/users")
    suspend fun getData(): Response<UserResponse>

}