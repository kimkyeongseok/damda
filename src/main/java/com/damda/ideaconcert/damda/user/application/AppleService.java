package com.damda.ideaconcert.damda.user.application;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppleService extends AbstractUserService<AppleUserRequest>{

    public AppleService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder, 
        JwtUtils jwtUtils,
        FileManager fileManager,
        AuthUserMailSender authUserMailSender

    ) {
        super(
            userRepository, 
            passwordEncoder, 
            jwtUtils,
            fileManager,
            authUserMailSender
        );
    }

    @Override
    public LoginResponse login(AppleUserRequest userRequest, String userAgent) {
        AppleUserRequest appleUserRequest = new AppleUserRequest(
            "Apple@"+ userRequest.getUserId(), 
             userRequest.getNickName(), 
             userRequest.getUserPw(),
             userRequest.getUserEmail(),
             userRequest.getUserPwCnf(), 
             userAgent,
             userRequest.getSelectReception()
         );
         if(!idCheck(appleUserRequest.getUserId())){
             return doSocialSignUp(appleUserRequest);
         }else{
            return doLogin(appleUserRequest);
         }      
    }

    @Override
    public LoginResponse signUp(AppleUserRequest t) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
