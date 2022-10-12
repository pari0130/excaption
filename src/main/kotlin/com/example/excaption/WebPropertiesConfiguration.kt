package com.example.excaption

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebPropertiesConfiguration {
    @Bean
    fun resources(): WebProperties.Resources {
        return WebProperties.Resources()
    }
}