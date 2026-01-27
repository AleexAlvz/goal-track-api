package com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.ActionExecution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActionExecutionRepository : JpaRepository<ActionExecution, Long> {
    fun findByActionId(actionId: Long): List<ActionExecution>
}