package com.br.aleexalvz.goal_track_api.presentation.dto.auth

import com.br.aleexalvz.goal_track_api.presentation.matcher.PasswordMatches
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@PasswordMatches
data class SignupRequest(
    @field:NotBlank(message = "Email é obrigatório")
    @field:Email(message = "Email inválido")
    val email: String,

    @field:NotBlank
    @field:Size(min = 8)
    @field:Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
        message = "Senha deve conter letra maiúscula, minúscula e número"
    )
    val password: String,

    @field:NotBlank
    val confirmPassword: String,

    @field:NotBlank(message = "Nome completo é obrigatório")
    @field:Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @field:Pattern(
        regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)+$",
        message = "Informe o nome completo (nome e sobrenome)"
    )
    val fullName: String
)