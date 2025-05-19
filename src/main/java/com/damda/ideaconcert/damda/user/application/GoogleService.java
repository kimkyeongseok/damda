package com.damda.ideaconcert.damda.user.application;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleService extends AbstractUserService<GoogleUserRequest> {
    
    public GoogleService(
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
    @Transactional
    public LoginResponse login(GoogleUserRequest userRequest, String userAgent) {
        GoogleUserRequest googleUserRequest = new GoogleUserRequest(
           "Google@"+ userRequest.getUserId(), 
            userRequest.getNickName(), 
            userRequest.getUserPw(),
            userRequest.getUserEmail(),
            userRequest.getUserPwCnf(), 
            userAgent,
            userRequest.getSelectReception()

        );
        if(!idCheck(googleUserRequest.getUserId())){
            return doSocialSignUp(googleUserRequest);
        }else{
           return doLogin(googleUserRequest);
        }      
    }
    @Override
    public LoginResponse signUp(GoogleUserRequest userRequest) {
        return null;
    }
    
}
