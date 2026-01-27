package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.application.service.ActionService
import com.br.aleexalvz.goal_track_api.presentation.dto.action.CreateActionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.action.UpdateActionRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/actions")
class ActionController(
    private val actionService: ActionService
) {

    @PostMapping
    fun create(@RequestBody request: CreateActionRequest) =
        actionService.create(request)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) =
        actionService.findById(id)

    @GetMapping("/goal/{goalId}")
    fun findByGoal(@PathVariable goalId: Long) =
        actionService.findByGoal(goalId)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: UpdateActionRequest
    ) = actionService.update(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) =
        actionService.delete(id)
}
