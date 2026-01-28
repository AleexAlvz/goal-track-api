package com.br.aleexalvz.goal_track_api.application.service

import com.br.aleexalvz.goal_track_api.domain.exception.EmailAlreadyExistsException
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.User
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.UserRepository
import com.br.aleexalvz.goal_track_api.infrastructure.security.JwtUtil
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.AuthResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.SignupRequest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun register(request: SignupRequest) {
        if (userRepository.findByEmail(request.email) != null) {
            throw EmailAlreadyExistsException()
        }

        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            fullName = request.fullName
        )

        userRepository.save(user)
    }

    fun login(email: String): AuthResponse {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found")

        val token = jwtUtil.generateToken(user.email)

        return AuthResponse(
            token = token,
            email = user.email,
            fullName = user.fullName
        )
    }
}