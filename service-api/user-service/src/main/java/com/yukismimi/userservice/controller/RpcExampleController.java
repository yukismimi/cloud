package com.yukismimi.userservice.controller;

import com.yukismimi.userservice.clients.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcExampleController {

    @Autowired
    private HelloClient helloClient;

    @GetMapping("/call")
    public String call() {
        return helloClient.hello();
    }
}
