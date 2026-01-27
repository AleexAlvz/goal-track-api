package com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Action
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActionRepository : JpaRepository<Action, Long> {
    fun findByGoalId(goalId: Long): List<Action>
}