package com.damda.ideaconcert.damda.config.Authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("code", "999");
        responseMap.put("message", "잘못된 접근 입니다.");
        Gson gson = new Gson();
        String json = gson.toJson(responseMap);
        PrintWriter out = response.getWriter();
        out.println(json);
    }

}
