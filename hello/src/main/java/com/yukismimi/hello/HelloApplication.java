package com.yukismimi.hello;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {

    @GetMapping("/hello")
    public Foo hello(){
        Foo foo = new Foo();
        foo.setFoo("foo");
        foo.setBar("bar");
        return foo;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Data
    private class Foo{
        private String foo;
        private String bar;
    }
}
