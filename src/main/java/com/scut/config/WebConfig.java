package com.scut.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        log.info("配置跨域...");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//允许所有路径
                        .allowedOrigins("http://localhost:5173")//允许前端的origin
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")//允许所有请求方法
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);//允许携带cookie
            }
        };
    }
}
