package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.application.service.AuthService
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.AuthResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.LoginRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.SignupRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val authenticationManager: AuthenticationManager
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
        val authToken = UsernamePasswordAuthenticationToken(
            loginRequest.email,
            loginRequest.password
        )

        authenticationManager.authenticate(authToken)

        return authService.login(loginRequest.email)
    }
}