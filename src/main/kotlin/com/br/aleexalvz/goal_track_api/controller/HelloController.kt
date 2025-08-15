package com.br.aleexalvz.goal_track_api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun helloWorld(): String{
        return "Hello world!"
    }
}