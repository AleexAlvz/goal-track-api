package com.br.aleexalvz.goal_track_api.presentation.dto.auth

import jakarta.validation.constraints.NotBlank

data class RefreshSessionRequest(
    @field:NotBlank(message = "refreshToken is required")
    val refreshToken: String
)