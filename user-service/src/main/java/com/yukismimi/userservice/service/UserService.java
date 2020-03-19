package com.yukismimi.userservice.service;

import com.yukismimi.userservice.entity.User;

public interface UserService {

    void register(User user);

    String login(User user);
}
