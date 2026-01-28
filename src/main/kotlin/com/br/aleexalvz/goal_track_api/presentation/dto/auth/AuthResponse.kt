package com.br.aleexalvz.goal_track_api.presentation.dto.auth

data class AuthResponse(
    val token: String,
    val email: String,
    val fullName: String
)