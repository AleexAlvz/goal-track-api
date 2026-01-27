package com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.ActionFrequencyEnum
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "actions")
data class Action(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    val goal: Goal,

    @Column(nullable = false)
    val title: String,

    @Column(length = 1000)
    val description: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val frequency: ActionFrequencyEnum,

    @Column(name = "creation_date", nullable = false)
    val creationDate: LocalDate,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @OneToMany(
        mappedBy = "action",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val executions: List<ActionExecution> = emptyList()
)
