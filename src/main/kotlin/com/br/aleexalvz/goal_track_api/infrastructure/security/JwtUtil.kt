package com.br.aleexalvz.goal_track_api.infrastructure.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    private val secret = "mysecretkey123456" // TODO replace it for better key

    private val authExpirationMs = 1000 * 60 * 15 // 15 minutes
    private val refreshExpirationMs = 1000 * 60 * 60 * 24 * 7 // 7 days
    private val tokenTypeClaimKey = "tokenType"

    fun generateAuthToken(email: String): String {
        return JWT.create()
            .withSubject(email)
            .withClaim(tokenTypeClaimKey, TokenType.AuthToken.type)
            .withExpiresAt(Date(System.currentTimeMillis() + authExpirationMs))
            .sign(Algorithm.HMAC256(secret))
    }

    fun generateRefreshToken(email: String): String {
        return JWT.create()
            .withSubject(email)
            .withClaim(tokenTypeClaimKey, TokenType.RefreshToken.type)
            .withExpiresAt(Date(System.currentTimeMillis() + refreshExpirationMs))
            .sign(Algorithm.HMAC256(secret))
    }

    fun validateToken(token: String, tokenType: TokenType): Boolean {
        return try {
            JWT.decode(token)
            JWT.require(Algorithm.HMAC256(secret))
                .withClaim(tokenTypeClaimKey, tokenType.type)
                .build()
                .verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getEmailFromToken(token: String): String {
        return JWT.decode(token).subject
    }
}