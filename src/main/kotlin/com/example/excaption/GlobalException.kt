package com.example.excaption

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalException(
    errorAttributes: ErrorAttributes,
    resources: WebProperties.Resources,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, resources, applicationContext) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all()) {
            logger.info("getRoutingFunction error $it")
            when (val error: Throwable = errorAttributes.getError(it)) {
                is CustomException ->
                    renderErrorResponse(error)
                else ->
                    badResponse(error)
            }
        }
    }

    private fun badResponse(error: Throwable): Mono<ServerResponse> {
        return ServerResponse.status(HttpStatus.OK).body(
            BodyInserters.fromValue(
                Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.message.toString(), null)
            )
        )
    }

    private fun renderErrorResponse(error: CustomException): Mono<ServerResponse> {
        return ServerResponse.status(HttpStatus.OK).body(
            BodyInserters.fromValue(
                Response(error.errorCode, error.message.toString(), null)
            )
        )
    }
}