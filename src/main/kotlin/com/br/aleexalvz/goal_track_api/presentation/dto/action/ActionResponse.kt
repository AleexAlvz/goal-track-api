package com.br.aleexalvz.goal_track_api.presentation.dto.action

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.entity.Action
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.ActionFrequencyEnum
import java.time.LocalDate

data class ActionResponse(
    val id: Long,
    val goalId: Long,
    val title: String,
    val description: String,
    val frequency: ActionFrequencyEnum,
    val creationDate: LocalDate,
    val endDate: LocalDate?
)

fun Action.toResponse() = ActionResponse(
    id = requireNotNull(id) { "id must not be null when mapping to response" },
    goalId = requireNotNull(goal.id) { "Goal id must not be null when mapping to response" },
    title = title,
    description = description,
    frequency = frequency,
    creationDate = creationDate,
    endDate = endDate
)