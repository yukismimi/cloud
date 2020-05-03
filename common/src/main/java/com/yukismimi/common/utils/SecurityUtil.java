package com.yukismimi.common.utils;

import com.yukismimi.common.component.IdUsernameAuthenticationFilter;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getUsername() {
        return ((IdUsernameAuthenticationFilter.IdUsernameInfo) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    public static Long getUserId() {
        String id = ((IdUsernameAuthenticationFilter.IdUsernameInfo) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
        return Long.valueOf(id);
    }
}
