package com.br.aleexalvz.goal_track_api.presentation.dto.execution

import java.time.LocalDate

data class UpdateActionExecutionRequest(
    val executionDate: LocalDate,
    val notes: String?
)