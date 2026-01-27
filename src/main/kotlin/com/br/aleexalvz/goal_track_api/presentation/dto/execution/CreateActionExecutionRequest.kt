package com.br.aleexalvz.goal_track_api.presentation.dto.execution

import java.time.LocalDate

data class CreateActionExecutionRequest(
    val actionId: Long,
    val executionDate: LocalDate,
    val notes: String?
)
