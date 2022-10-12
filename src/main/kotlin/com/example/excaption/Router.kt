package com.example.excaption

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.router
import java.net.URI

@Configuration
class Router(
    private val handler: Handler
) {
    @Bean
    fun exportRoutes() = router {
        accept(TEXT_HTML).nest {
            GET("/") { permanentRedirect(URI("index.html")).build() }
        }
        accept(APPLICATION_JSON).nest {
            GET("/product", handler::findProduct)
        }
    }
}