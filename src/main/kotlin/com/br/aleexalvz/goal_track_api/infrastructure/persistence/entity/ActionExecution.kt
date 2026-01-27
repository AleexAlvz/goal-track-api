package com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "action_executions")
data class ActionExecution(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", nullable = false)
    val action: Action,

    @Column(name = "execution_date", nullable = false)
    val executionDate: LocalDate,

    @Column(length = 1000)
    val notes: String? = null
)