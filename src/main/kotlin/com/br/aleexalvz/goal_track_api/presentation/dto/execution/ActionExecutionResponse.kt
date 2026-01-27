package com.br.aleexalvz.goal_track_api.presentation.dto.execution

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.ActionExecution
import java.time.LocalDate

data class ActionExecutionResponse(
    val id: Long,
    val actionId: Long,
    val executionDate: LocalDate,
    val notes: String?
)

fun ActionExecution.toResponse() = ActionExecutionResponse(
    id = requireNotNull(id) { "id must not be null when mapping to response" },
    actionId = requireNotNull(action.id) { "Action id must not be null when mapping to response" },
    executionDate = executionDate,
    notes = notes
)