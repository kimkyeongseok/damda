package com.damda.ideaconcert.damda.utils;

import javax.servlet.http.HttpServletRequest;

public class DevicesCheck {
    public static String getDevices(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if(userAgent.indexOf("MOBILE") > -1) {
            return "MOBILE";
        } 
        else {
            return "PC";
        }
    }

}
