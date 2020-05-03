package com.yukismimi.orderservice.client;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.orderservice.client.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/info/{username}")
    CommonResult<User> getUserInfo(@PathVariable String username);
}
