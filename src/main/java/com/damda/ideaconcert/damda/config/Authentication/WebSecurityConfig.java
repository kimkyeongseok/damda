package com.damda.ideaconcert.damda.config.Authentication;

import java.util.ArrayList;
import java.util.List;

import com.damda.ideaconcert.damda.user.application.JwtUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private final JwtUtils jwtUtils;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable() // REST API 개발이기에 기본 설정 해제
            .csrf().disable() // csrf 보안 토큰 해제
            .headers().frameOptions().sameOrigin()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 토큰 기반 인증이므로 세션 기능 해제
            .and()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .logout().logoutUrl("/api/account/user/logout").logoutSuccessUrl("/").deleteCookies("_dawt")
            .and()
            .authorizeRequests()
                .antMatchers("/api/admin/management/**").hasRole("ADMIN")
                .antMatchers("/api/account/user/check").hasRole("USER")
                .antMatchers("/api/account/user/profile").hasRole("USER")
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/helps/deactivate").hasRole("USER")
                .antMatchers("/swagger-resources/**","/swagger-ui.html","/v2/api-docs","/webjars/**")
                .permitAll()
                .anyRequest().permitAll()
            .and()
            .addFilterBefore(
                new UserAuthenticationFilter(jwtUtils), 
                UsernamePasswordAuthenticationFilter.class
            )
            .exceptionHandling() 
            .authenticationEntryPoint(authenticationEntryPoint);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        List<String> origins = new ArrayList<>();
        origins.add("https://www.hidamda.com");
        origins.add("https://hidamda.com");
        origins.add("https://test.hidamda.com");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        // configuration.setAllowedOrigins(origins);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}