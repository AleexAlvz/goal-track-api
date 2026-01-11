package com.br.aleexalvz.goal_track_api.service

import com.br.aleexalvz.goal_track_api.exception.EmailAlreadyExistsException
import com.br.aleexalvz.goal_track_api.model.SignupRequest
import com.br.aleexalvz.goal_track_api.model.User
import com.br.aleexalvz.goal_track_api.repository.UserRepository
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