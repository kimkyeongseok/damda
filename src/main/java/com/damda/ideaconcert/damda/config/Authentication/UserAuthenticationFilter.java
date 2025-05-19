package com.damda.ideaconcert.damda.config.Authentication;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.damda.ideaconcert.damda.user.application.JwtUtils;
import com.damda.ideaconcert.damda.user.application.Payload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromCookies(request);
            if (StringUtils.hasText(token) && jwtUtils.isValidToken(token)) {
                Payload payload = jwtUtils.getPayloadFromJWT(token);
                
                UserAuthentication userDetails = new UserAuthentication(
                    payload.getUserId(),
                    payload.getUserPk(),
                    Arrays.asList(new SimpleGrantedAuthority(payload.getRole()))
                ); 
                UsernamePasswordAuthenticationToken authentication 
                    = new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null, 
                        userDetails.getAuthorities()
                    );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                ResponseCookie cookie = ResponseCookie.from("_dawt", "")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(0)
                    .build();
                response.addHeader("Set-Cookie", cookie.toString());
            }
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
        }
       filterChain.doFilter(request, response);
    }

    private String getTokenFromCookies(HttpServletRequest request) {
        String token = "";
        Cookie[] cookies =  request.getCookies();

        for(int i=0; i < cookies.length; i++){
            if(cookies[i].getName().equals("_dawt")){
                return token = cookies[i].getValue();
            }
        }
        return token;
    }
}
