package com.br.aleexalvz.goal_track_api.application.service

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Action
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.ActionRepository
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.GoalRepository
import com.br.aleexalvz.goal_track_api.presentation.dto.action.ActionResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.action.CreateActionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.action.UpdateActionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.action.toResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ActionService(
    private val actionRepository: ActionRepository,
    private val goalRepository: GoalRepository
) {

    fun create(request: CreateActionRequest): ActionResponse {
        val goal = goalRepository.findById(request.goalId)
            .orElseThrow { EntityNotFoundException("Goal ${request.goalId} not found") }

        val action = Action(
            goal = goal,
            title = request.title,
            description = request.description,
            frequency = request.frequency,
            creationDate = LocalDate.now(),
        )

        return actionRepository.save(action).toResponse()
    }

    fun findById(id: Long): ActionResponse =
        actionRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Action $id not found") }
            .toResponse()

    fun findByGoal(goalId: Long): List<ActionResponse> =
        actionRepository.findByGoalId(goalId).map { it.toResponse() }

    fun update(id: Long, request: UpdateActionRequest): ActionResponse {
        val action = actionRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Action $id not found") }

        val updated = action.copy(
            title = request.title,
            description = request.description,
            frequency = request.frequency,
            endDate = request.endDate
        )

        return actionRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!actionRepository.existsById(id)) {
            throw EntityNotFoundException("Action $id not found")
        }
        actionRepository.deleteById(id)
    }
}
