package com.br.aleexalvz.goal_track_api.domain.exception;

import com.br.aleexalvz.goal_track_api.domain.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: MethodArgumentNotValidException): ApiError {

        val fieldErrors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }

        return ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Validation Error",
            message = "Invalid request parameters",
            fieldErrors = fieldErrors
        )
    }

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleBadCredentials(): ApiError {
        return ApiError(
            status = 401,
            error = "Invalid credentials",
            message = "Email or password is incorrect"
        )
    }

    @ExceptionHandler(DisabledException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleDisabledUser(): ApiError {
        return ApiError(
            status = 403,
            error = "User disabled",
            message = "User account is disabled"
        )
    }
}