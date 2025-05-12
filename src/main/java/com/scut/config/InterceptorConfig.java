package com.scut.config;

import com.scut.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("跨域拦截器加载...");
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:5173")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("拦截器加载...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/test/**")
                .addPathPatterns("/course/**")
                .addPathPatterns("/plan/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }
}
