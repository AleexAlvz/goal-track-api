package com.br.aleexalvz.goal_track_api.controller

import com.br.aleexalvz.goal_track_api.model.User
import com.br.aleexalvz.goal_track_api.repository.UserRepository
import com.br.aleexalvz.goal_track_api.security.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class AuthRequest(val email: String, val password: String)
data class AuthResponse(val token: String)

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    @PostMapping("/register")
    fun register(@RequestBody authRequest: AuthRequest): String {
        if (userRepository.findByEmail(authRequest.email) != null) {
            return "Email already exists"
        }
        val encodedPassword = passwordEncoder.encode(authRequest.password)
        userRepository.save(
            User(email = authRequest.email, password = encodedPassword)
        )
        return "User registered successfully"
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): AuthResponse {
        val authToken = UsernamePasswordAuthenticationToken(request.email, request.password)
        authenticationManager.authenticate(authToken) // valida email e senha
        val token = jwtUtil.generateToken(request.email)
        return AuthResponse(token)
    }
}