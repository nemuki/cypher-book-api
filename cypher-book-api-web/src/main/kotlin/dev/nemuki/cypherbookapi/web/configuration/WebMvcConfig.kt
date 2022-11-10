package dev.nemuki.cypherbookapi.web.configuration

import dev.nemuki.cypherbookapi.web.interceptor.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(RequestInterceptor())
            .addPathPatterns("/books*")
    }
}
