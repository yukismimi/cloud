package com.yukismimi.userservice.service.impl;

import com.yukismimi.userservice.domain.UserDetailz;
import com.yukismimi.userservice.entity.User;
import com.yukismimi.userservice.repository.UserRepository;
import com.yukismimi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public void register(User user) {
    }

    @Override
    public String login(User user){
        return null;
    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
}
