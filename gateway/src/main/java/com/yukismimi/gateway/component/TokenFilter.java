package com.yukismimi.gateway.component;

import com.yukismimi.gateway.client.UserClient;
import com.yukismimi.gateway.client.dto.UserDTO;
import com.yukismimi.gateway.configuration.TokenIgnoreUrlsConfiguration;
import com.yukismimi.gateway.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private TokenIgnoreUrlsConfiguration tokenIgnoreUrlsConfiguration;
    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();
        boolean isIgnoredUrl = tokenIgnoreUrlsConfiguration.getUrls()
                .stream()
                .anyMatch(uri::matches);
        if (isIgnoredUrl){
            return chain.filter(exchange);
        }

        String token = serverHttpRequest.getHeaders().getFirst("token");
        if(token != null){
            String username = jwtTokenUtil.getUserNameFromToken(token);
            UserDTO userDTO = userClient.getUserInfo(username).getData();
            if (userDTO != null) {
                serverHttpRequest.mutate()
                        .header("id", String.valueOf(userDTO.getUserId()))
                        .header("username", username)
                        .build();
//                serverHttpRequest.getHeaders().set("id", String.valueOf(userDTO.getUserId()));
//                serverHttpRequest.getHeaders().set("username", username);
                return chain.filter(exchange);
            }
        }
        return exchange.getResponse().writeAndFlushWith(s -> s.onError(new Exception()));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
