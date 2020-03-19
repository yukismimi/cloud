package com.yukismimi.consul.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("hello-service")
public interface HelloClient {

    @GetMapping("/hello")
    String hello();
}
