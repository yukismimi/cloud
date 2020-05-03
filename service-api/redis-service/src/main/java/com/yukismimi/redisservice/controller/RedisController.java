package com.yukismimi.redisservice.controller;

import com.yukismimi.redisservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping("/token/{key}")
    public String getToken(@PathVariable String key) {
        return redisService.get(key);
    }

    @PostMapping("/token")
    public String setToken(@RequestParam String key, @RequestParam String token, @RequestParam long expire) {
        redisService.set(key, token);
        redisService.expire(key, expire);
        return "success";
    }
}
