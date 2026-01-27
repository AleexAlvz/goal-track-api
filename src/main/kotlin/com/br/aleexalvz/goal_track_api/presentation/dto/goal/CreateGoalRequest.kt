package com.br.aleexalvz.goal_track_api.presentation.dto.goal

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalCategoryEnum

data class CreateGoalRequest(
    val title: String,
    val description: String?,
    val category: GoalCategoryEnum,
)