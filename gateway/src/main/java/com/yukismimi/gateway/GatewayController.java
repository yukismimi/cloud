package com.yukismimi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/fallback")
    public String fallback() {
        return "fallback nothing";
    }
}

