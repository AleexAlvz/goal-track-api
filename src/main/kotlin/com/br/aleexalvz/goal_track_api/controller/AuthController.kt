package com.br.aleexalvz.goal_track_api.controller

import com.br.aleexalvz.goal_track_api.model.AuthResponse
import com.br.aleexalvz.goal_track_api.model.LoginRequest
import com.br.aleexalvz.goal_track_api.model.SignupRequest
import com.br.aleexalvz.goal_track_api.repository.UserRepository
import com.br.aleexalvz.goal_track_api.security.JwtUtil
import com.br.aleexalvz.goal_track_api.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid @RequestBody request: SignupRequest) {
        authService.register(request)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest
    ): AuthResponse {

        val authToken = UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        authenticationManager.authenticate(authToken)
        val token = jwtUtil.generateToken(loginRequest.email)
        return AuthResponse(token = token)
    }
}