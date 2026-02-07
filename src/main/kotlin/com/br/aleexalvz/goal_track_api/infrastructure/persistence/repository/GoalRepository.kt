package com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Goal
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository : JpaRepository<Goal, Long> {
    fun findByStatus(status: GoalStatusEnum): List<Goal>
}