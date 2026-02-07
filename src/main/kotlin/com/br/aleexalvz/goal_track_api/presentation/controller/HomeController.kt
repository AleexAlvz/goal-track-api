package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.GoalRepository
import com.br.aleexalvz.goal_track_api.presentation.dto.home.GetHomeResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.home.GoalStatusCard
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home")
class HomeController(
    val goalRepository: GoalRepository
) {
    @GetMapping
    fun getHome(): GetHomeResponse {
        val goalsInProgress = goalRepository.findByStatus(GoalStatusEnum.IN_PROGRESS)
        return GetHomeResponse(
            goalStatusCard = GoalStatusCard(
                goalsInProgress = goalsInProgress.size
            )
        )
    }
}