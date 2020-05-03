package com.yukismimi.configclient;

import com.yukismimi.configclient.entity.User;
import com.yukismimi.configclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ConfigClientApplication {

    @Value("${spring.cloud}")
    private String info;

    @Autowired
    private UserRepository repository;

    @GetMapping("/config/info")
    public String info(){
        return this.info;
    }

    @GetMapping("/api/test")
    public List<User> test() {
        return repository.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
