package com.br.aleexalvz.goal_track_api.infrastructure.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    private val secret = "mysecretkey123456" // TODO replace it for better key
    private val expirationMs = 1000 * 60 * 60 // 1 hora

    fun generateToken(email: String): String {
        return JWT.create()
            .withSubject(email)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationMs))
            .sign(Algorithm.HMAC256(secret))
    }

    fun validateToken(token: String): Boolean {
        return try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getEmailFromToken(token: String): String {
        return JWT.decode(token).subject
    }
}