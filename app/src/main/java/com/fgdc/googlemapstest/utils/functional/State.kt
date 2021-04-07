package com.fgdc.googlemapstest.utils.functional

import com.fgdc.googlemapstest.utils.exception.ErrorHandler

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : State<Nothing>()

class ErrorNoConnection(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.NETWORK_ERROR_MESSAGE
) : State<Nothing>()

class BadRequest(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : State<Nothing>()
