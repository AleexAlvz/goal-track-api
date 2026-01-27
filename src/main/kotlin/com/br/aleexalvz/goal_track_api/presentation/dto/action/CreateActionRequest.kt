package com.br.aleexalvz.goal_track_api.presentation.dto.action

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.ActionFrequencyEnum

data class CreateActionRequest(
    val goalId: Long,
    val title: String,
    val description: String,
    val frequency: ActionFrequencyEnum
)