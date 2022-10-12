package com.example.excaption

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class Service {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun findProduct(): Mono<String> {
        // throw CustomException("service custom exception test", 12345)
        throw Exception("service exception test")
        logger.info("Service ========")
        return Mono.just("findProduct service")
    }
}