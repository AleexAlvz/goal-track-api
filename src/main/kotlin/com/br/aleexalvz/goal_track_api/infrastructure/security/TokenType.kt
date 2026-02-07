package com.br.aleexalvz.goal_track_api.infrastructure.security

enum class TokenType(val type: String) {
    AuthToken("auth"),
    RefreshToken("refresh")
}