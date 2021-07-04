package pl.mwezyk.pugsbankapi.currency

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplateConfig(): RestTemplate {
        return RestTemplateBuilder().build();
    }
}