package com.br.aleexalvz.goal_track_api.presentation.dto.action

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.ActionFrequencyEnum
import java.time.LocalDate

data class UpdateActionRequest(
    val title: String,
    val description: String,
    val frequency: ActionFrequencyEnum,
    val endDate: LocalDate?
)