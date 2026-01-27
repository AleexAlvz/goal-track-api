package com.br.aleexalvz.goal_track_api.application.service

import com.br.aleexalvz.goal_track_api.domain.exception.EmailAlreadyExistsException
import com.br.aleexalvz.goal_track_api.presentation.dto.auth.SignupRequest
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.User
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(request: SignupRequest) {
        if (userRepository.findByEmail(request.email) != null) {
            throw EmailAlreadyExistsException()
        }

        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password)
        )

        userRepository.save(user)
    }
}