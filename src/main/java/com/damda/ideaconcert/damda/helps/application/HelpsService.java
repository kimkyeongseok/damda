package com.damda.ideaconcert.damda.helps.application;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.damda.ideaconcert.damda.helps.representation.AuthResponse;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;
import com.damda.ideaconcert.damda.user.representation.ResetPasswordRequest;

import org.springframework.stereotype.Service;

@Service
public interface HelpsService {
    LoginResponse sendEmailForAuthUser(String userId, String nickName);
    boolean verifyEmailForAuthUser(String userId, String authKey);
    boolean verifyEmailForResetPassword(String userId, String authKey);
    AuthResponse resetPassword(@Valid ResetPasswordRequest userRequest);
    AuthResponse sendEmailForResetPassword(String userId, String nickName);
    AuthResponse deactivateUserAccount(String jwt, HttpServletResponse servletResponse);
    AuthResponse sendEmailForFindUser(String userEmail);
}
