package com.br.aleexalvz.goal_track_api.presentation.dto.goal

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalCategoryEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import java.time.LocalDate

data class UpdateGoalRequest(
    val title: String,
    val description: String?,
    val category: GoalCategoryEnum,
    val endDate: LocalDate?,
    val status: GoalStatusEnum
)