package com.yukismimi.userservice.config;

//import com.yukismimi.security.config.SecurityConfig;

import com.yukismimi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

//@Configuration
//@EnableWebSecurity
//public class UserSecurityConfig extends SecurityConfig {
//
//    @Autowired
//    private UserService userService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取登录用户信息
//        return username -> userService.getUserByName(username);
//    }
//
//}
