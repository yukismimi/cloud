package com.yukismimi.gateway.client;

import com.yukismimi.gateway.api.CommonResult;
import com.yukismimi.gateway.client.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("user/info")
    CommonResult<UserDTO> getUserInfo(@RequestParam("username") String username);
}
