package com.yukismimi.common.config;

import com.yukismimi.common.utils.SecurityUtil;
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
            System.out.println(SecurityUtil.getUsername());
            System.out.println(SecurityUtil.getUserId());
            requestTemplate.header("id", SecurityUtil.getUserId().toString());
            requestTemplate.header("username", SecurityUtil.getUsername());
        };
    }
}
