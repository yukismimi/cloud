package com.yukismimi.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "custom-filter")
@Component
public class UrlsConfig {

    private String[] urls;
}
