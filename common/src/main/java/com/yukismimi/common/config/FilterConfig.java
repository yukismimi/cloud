package com.yukismimi.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class FilterConfig {

    @Autowired
    private UrlsConfig urlsConfig;

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean(){
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("user-retrieve-filter");
        Optional.ofNullable(urlsConfig)
                .map(UrlsConfig::getUrls)
                .ifPresent(bean::addUrlPatterns);
        return bean;
    }
}
