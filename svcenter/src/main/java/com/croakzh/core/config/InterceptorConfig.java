package com.croakzh.core.config;

import com.croakzh.interceptor.ProcessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * jwt
 *
 * @author croakzh
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public HandlerInterceptor getProcessInterceptor() {
        return new ProcessInterceptor();
    }

    /**
     * 增加拦截器
     *
     * @param registry {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getProcessInterceptor()).addPathPatterns("/**");
    }

}
