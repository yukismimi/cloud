package com.yukismimi.payservice.config;

import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.alipay.api.AlipayConstants.*;

@Configuration
public class PayConfig {

    @Value("${alipay.app_id}")
    private String APP_ID;
    @Value("${alipay.url}")
    private String URL;
    @Value("${alipay.private_key}")
    private String APP_PRIVATE_KEY;
    @Value("${alipay.public_key}")
    private String ALIPAY_PUBLIC_KEY;

    @Bean
    public DefaultAlipayClient defaultAlipayClient() {
        return new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT_JSON, CHARSET_UTF8, ALIPAY_PUBLIC_KEY, SIGN_TYPE_RSA2);
    }
}
