package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.infrastructure.persistence.repository.GoalRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    goalRepository: GoalRepository
) {

    @GetMapping("home")
    fun getHome() {

    }
}