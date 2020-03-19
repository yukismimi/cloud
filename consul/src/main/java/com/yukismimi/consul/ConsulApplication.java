package com.yukismimi.consul;

import com.yukismimi.consul.clients.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulApplication {

    @Value("${server.port}")
    private String port;

    @Autowired
    private HelloClient helloClient;

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/port-info")
    public String info() {
        return this.port;
    }

    @GetMapping("/hi")
    public String hi(){
        return helloClient.hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }

}
