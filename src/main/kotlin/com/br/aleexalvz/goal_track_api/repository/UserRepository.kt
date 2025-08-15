package com.br.aleexalvz.goal_track_api.repository

import com.br.aleexalvz.goal_track_api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}