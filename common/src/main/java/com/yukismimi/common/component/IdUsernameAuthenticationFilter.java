package com.yukismimi.common.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查gateway的请求是否包含id与username
 */
@Slf4j
@Component
public class IdUsernameAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String id = request.getHeader("id");
        String username = request.getHeader("username");
        if (id != null && username != null) {
            log.info("id:{}, username:{}", id, username);
            IdUsernameInfo idUsernameInfo = new IdUsernameInfo(id, username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(idUsernameInfo, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Data
    @AllArgsConstructor
    public class IdUsernameInfo {
        private String id;
        private String username;
    }

}
