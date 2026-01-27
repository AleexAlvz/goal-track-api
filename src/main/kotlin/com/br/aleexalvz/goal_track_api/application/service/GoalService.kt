package com.br.aleexalvz.goal_track_api.application.service

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Goal
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.GoalRepository
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.CreateGoalRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.GoalResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.UpdateGoalRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.toResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GoalService(
    private val goalRepository: GoalRepository
) {

    fun create(request: CreateGoalRequest): GoalResponse {
        val goal = Goal(
            title = request.title,
            description = request.description,
            category = request.category,
            creationDate = LocalDate.now(),
            status = GoalStatusEnum.IN_PROGRESS
        )
        return goalRepository.save(goal).toResponse()
    }

    fun findById(id: Long): GoalResponse =
        goalRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Goal $id not found") }
            .toResponse()

    fun findAll(): List<GoalResponse> =
        goalRepository.findAll().map { it.toResponse() }

    fun update(id: Long, request: UpdateGoalRequest): GoalResponse {
        val goal = goalRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Goal $id not found") }

        val updated = goal.copy(
            title = request.title,
            description = request.description,
            category = request.category,
            endDate = request.endDate,
            status = request.status
        )

        return goalRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!goalRepository.existsById(id)) {
            throw EntityNotFoundException("Goal $id not found")
        }
        goalRepository.deleteById(id)
    }
}
