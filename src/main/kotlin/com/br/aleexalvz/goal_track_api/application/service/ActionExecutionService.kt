package com.br.aleexalvz.goal_track_api.application.service

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.ActionExecution
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.ActionExecutionRepository
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.ActionRepository
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.ActionExecutionResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.CreateActionExecutionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.UpdateActionExecutionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.toResponse
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ActionExecutionService(
    private val actionExecutionRepository: ActionExecutionRepository,
    private val actionRepository: ActionRepository
) {

    fun create(request: CreateActionExecutionRequest): ActionExecutionResponse {
        val action = actionRepository.findById(request.actionId)
            .orElseThrow { EntityNotFoundException("Action ${request.actionId} not found") }

        val execution = ActionExecution(
            action = action,
            executionDate = request.executionDate,
            notes = request.notes
        )

        return actionExecutionRepository.save(execution).toResponse()
    }

    fun findByAction(actionId: Long): List<ActionExecutionResponse> =
        actionExecutionRepository.findByActionId(actionId)
            .map { it.toResponse() }

    @Transactional
    fun update(
        executionId: Long,
        request: UpdateActionExecutionRequest
    ): ActionExecutionResponse {

        val existing = actionExecutionRepository.findById(executionId)
            .orElseThrow { IllegalArgumentException("ActionExecution not found") }

        val updated = existing.copy(
            executionDate = request.executionDate,
            notes = request.notes
        )

        return actionExecutionRepository.save(updated).toResponse()
    }

    @Transactional
    fun delete(executionId: Long) {
        if (!actionExecutionRepository.existsById(executionId)) {
            throw IllegalArgumentException("ActionExecution not found")
        }
        actionExecutionRepository.deleteById(executionId)
    }
}
