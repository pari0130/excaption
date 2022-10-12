package com.example.excaption

import org.springframework.http.HttpStatus

class Response(
    var code: Int,
    var message: String,
    var result: Any? = null
) {
    companion object {
        fun ok(result: Any?) = Response(HttpStatus.OK.value(), "OK", result)
        fun noValue() = Response(1000001, "No Value Present", null)
        fun unknownException(message: String) = Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null)
    }
}