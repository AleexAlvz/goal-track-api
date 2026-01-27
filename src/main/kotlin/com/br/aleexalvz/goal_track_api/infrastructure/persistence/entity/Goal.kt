package com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalCategoryEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "goals")
data class Goal(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(length = 1000)
    val description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: GoalCategoryEnum,

    @Column(name = "creation_date", nullable = false)
    val creationDate: LocalDate,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: GoalStatusEnum,

    @OneToMany(
        mappedBy = "goal",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val actions: List<Action> = emptyList()
)
