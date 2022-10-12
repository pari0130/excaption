package com.example.excaption

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class Handler(
    private val service: Service
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun findProduct(request: ServerRequest): Mono<ServerResponse> {
        logger.info(request.toString())
        return Mono.just(request).flatMap {
            logger.info("Handler ======== req : $it")
            service.findProduct()
        }.flatMap {
            ServerResponse.ok().body(Mono.just(Response.ok(it)))
        }
//            .onErrorResume {
//            logger.error(it.message.toString())
//            when (it) {
//                is CustomException -> {
//                    ServerResponse.ok().body(Mono.just(Response(it.errorCode, it.message.toString(), null)))
//                }
//                else -> ServerResponse.ok().body(Mono.just(Response.unknownException(it.message.toString())))
//            }
//        }
    }
}

