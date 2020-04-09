package com.yukismimi.common.filter;

import cn.hutool.json.JSONUtil;
import com.yukismimi.common.api.CommonResult;
import com.yukismimi.common.api.ResultCode;
import com.yukismimi.common.utils.ThreadLocalUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//@Component("user-retrieve-filter")
public class UserRetrieveFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        int userId = Integer.parseInt(Optional.ofNullable(request.getHeader("id")).orElse("0"));
        String username = request.getHeader("username");
        if (username == null || username.isEmpty() || userId <= 0) {
            response.setContentType("application/json;charset=UTF8");
            response.setCharacterEncoding("UTF-8");
            CommonResult<Void> result = CommonResult.failed(ResultCode.FORBIDDEN);
            response.getWriter().println(JSONUtil.parse(result));
            response.getWriter().flush();
            return;
        }
        ThreadLocalUtils.setCurrentThreadUsername(username);
        ThreadLocalUtils.setCurrentThreadUserId(userId);
        doFilter(request, response, filterChain);
    }
}
