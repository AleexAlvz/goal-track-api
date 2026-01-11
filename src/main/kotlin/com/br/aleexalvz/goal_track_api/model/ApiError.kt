package com.br.aleexalvz.goal_track_api.model;

data class ApiError(
    val status: Int,
    val error: String,
    val message: String,
    val fieldErrors: Map<String, String>? = null
)