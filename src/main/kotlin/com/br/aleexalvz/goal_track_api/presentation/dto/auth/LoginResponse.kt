package com.br.aleexalvz.goal_track_api.presentation.dto.auth

data class LoginResponse(
    val refreshToken: String,
    val authToken: String,
    val email: String,
    val fullName: String
)