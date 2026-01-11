package com.br.aleexalvz.goal_track_api.matcher

import com.br.aleexalvz.goal_track_api.model.SignupRequest
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordMatchesValidator :
    ConstraintValidator<PasswordMatches, SignupRequest> {

    override fun isValid(
        value: SignupRequest?,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null) return true
        return value.password == value.confirmPassword
    }
}