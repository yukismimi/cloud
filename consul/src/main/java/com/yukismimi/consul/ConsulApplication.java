package com.yukismimi.consul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConsulApplication {

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/port-info")
    public String info() {
        return this.port;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }

}
