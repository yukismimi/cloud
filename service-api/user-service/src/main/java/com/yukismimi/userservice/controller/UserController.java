package com.yukismimi.userservice.controller;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.userservice.dto.UserDto;
import com.yukismimi.userservice.dto.UserLoginDTO;
import com.yukismimi.userservice.entity.User;
import com.yukismimi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = service.login(userLoginDTO);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        return CommonResult.success(token);
    }

    @PostMapping("/register")
    public CommonResult<Void> register(User user) {
        service.registry(user);
        return CommonResult.success(null, "注册成功");
    }

    @GetMapping("/info")
    public CommonResult<UserDto> getUserInfo(@RequestParam("username") String username) {
        UserDto userDto = service.getInfo(username);
        return CommonResult.success(userDto);
    }

}
