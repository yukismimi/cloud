package com.yukismimi.common.utils;

public class ThreadLocalUtils {

    private static ThreadLocal<String> usernameThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    public static String getCurrentThreadUsername() {
        return usernameThreadLocal.get();
    }

    public static void setCurrentThreadUsername(String username) {
        ThreadLocalUtils.usernameThreadLocal.set(username);
    }

    public static Long getCurrentThreadUserId() {
        return userIdThreadLocal.get();
    }

    public static void setCurrentThreadUserId(long userId) {
        ThreadLocalUtils.userIdThreadLocal.set(userId);
    }
}
