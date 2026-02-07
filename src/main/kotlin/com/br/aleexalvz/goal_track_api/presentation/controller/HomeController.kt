package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.model.GoalStatusEnum
import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.GoalRepository
import com.br.aleexalvz.goal_track_api.presentation.dto.home.GetHomeResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.home.GoalStatusCard
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    val goalRepository: GoalRepository
) {
    @GetMapping("home")
    fun getHome(): GetHomeResponse {
        val goalsInProgress = goalRepository.findByStatus(GoalStatusEnum.IN_PROGRESS)
        return GetHomeResponse(
            goalStatusCard = GoalStatusCard(
                goalsInProgress = goalsInProgress.size
            )
        )
    }
}