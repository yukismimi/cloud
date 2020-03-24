package com.yukismimi.userservice.service;

import com.yukismimi.userservice.domain.UserDetailz;
import com.yukismimi.userservice.dto.UserDto;
import com.yukismimi.userservice.entity.User;

public interface UserService {

    String login(String username, String password);

    void registry(User user);

    UserDto getInfo(String username);

    UserDetailz getUserByName(String username);
}
