package com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val fullName: String,

    @Column(nullable = false)
    val role: String = "USER"
)