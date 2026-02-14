package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.application.service.GoalService
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.CreateGoalRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.GetAllGoalsResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.GoalResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.goal.UpdateGoalRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goals")
class GoalController(
    private val goalService: GoalService
) {

    @PostMapping
    fun create(@RequestBody request: CreateGoalRequest): GoalResponse =
        goalService.create(request)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): GoalResponse =
        goalService.findById(id)

    @GetMapping
    fun findAll(): GetAllGoalsResponse = GetAllGoalsResponse(
        goals = goalService.findAll()
    )

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: UpdateGoalRequest
    ): GoalResponse =
        goalService.update(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        goalService.delete(id)
    }
}
