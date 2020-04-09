package com.yukismimi.common.config;

import com.yukismimi.common.utils.ThreadLocalUtils;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FeignConfig {

    @Bean
    @Lazy
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            System.out.println(ThreadLocalUtils.getCurrentThreadUserId().toString());
            System.out.println(ThreadLocalUtils.getCurrentThreadUsername());
            requestTemplate.header("id", ThreadLocalUtils.getCurrentThreadUserId().toString());
            requestTemplate.header("username", ThreadLocalUtils.getCurrentThreadUsername());
        };
    }
}
