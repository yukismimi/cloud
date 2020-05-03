package com.yukismimi.userservice.service.impl;

import com.yukismimi.common.exception.Asserts;
import com.yukismimi.common.utils.JwtTokenUtil;
import com.yukismimi.userservice.domain.UserDetailz;
import com.yukismimi.userservice.dto.UserDto;
import com.yukismimi.userservice.dto.UserLoginDTO;
import com.yukismimi.userservice.entity.User;
import com.yukismimi.userservice.repository.UserRepository;
import com.yukismimi.userservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        UserDetailz userDetailz = getUserByName(userLoginDTO.getUsername());
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), userDetailz.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetailz, null, userDetailz.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetailz);
    }

    @Override
    public void registry(User _user) {
        User user = repository.findByUsername(_user.getUsername());
        if (user != null)
            Asserts.fail("用户名已存在");
        String encodedPassword = passwordEncoder.encode(_user.getPassword());
        _user.setPassword(encodedPassword);
        repository.save(_user);
    }

    @Override
    public UserDto getInfo(String username) {
        User user = repository.findByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public UserDetailz getUserByName(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            return new UserDetailz(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
