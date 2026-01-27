package com.br.aleexalvz.goal_track_api.presentation.dto.goal

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Goal
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalCategoryEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import java.time.LocalDate

data class GoalResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val category: GoalCategoryEnum,
    val creationDate: LocalDate,
    val endDate: LocalDate?,
    val status: GoalStatusEnum
)

fun Goal.toResponse() = GoalResponse(
    id = requireNotNull(id) { "Goal id must not be null when mapping to response" },
    title = title,
    description = description,
    category = category,
    creationDate = creationDate,
    endDate = endDate,
    status = status
)
