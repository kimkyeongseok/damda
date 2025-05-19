package com.damda.ideaconcert.damda.utils;

import javax.servlet.http.HttpServletRequest;

public interface UserAgent {
    public static String getUserAgent(HttpServletRequest request){
        return request.getHeader("User-Agent");
    }
}
