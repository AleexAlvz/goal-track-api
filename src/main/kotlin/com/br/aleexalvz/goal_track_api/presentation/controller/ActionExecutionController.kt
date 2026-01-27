package com.br.aleexalvz.goal_track_api.presentation.controller

import com.br.aleexalvz.goal_track_api.application.service.ActionExecutionService
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.ActionExecutionResponse
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.CreateActionExecutionRequest
import com.br.aleexalvz.goal_track_api.presentation.dto.execution.UpdateActionExecutionRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/action-executions")
class ActionExecutionController(
    private val actionExecutionService: ActionExecutionService
) {

    @PostMapping
    fun create(@RequestBody request: CreateActionExecutionRequest) =
        actionExecutionService.create(request)

    @GetMapping("/action/{actionId}")
    fun findByAction(@PathVariable actionId: Long) =
        actionExecutionService.findByAction(actionId)

    @PutMapping("/{executionId}")
    fun update(
        @PathVariable executionId: Long,
        @RequestBody request: UpdateActionExecutionRequest
    ): ActionExecutionResponse =
        actionExecutionService.update(executionId, request)

    @DeleteMapping("/{executionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable executionId: Long
    ) {
        actionExecutionService.delete(executionId)
    }
}
