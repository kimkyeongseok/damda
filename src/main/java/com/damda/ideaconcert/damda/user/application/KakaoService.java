package com.damda.ideaconcert.damda.user.application;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KakaoService extends AbstractUserService<KakaoUserRequest> {
    public KakaoService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder, 
        JwtUtils jwtUtils,
        FileManager fileManager,
        AuthUserMailSender authUserMailSender
        ) {
        super(userRepository, passwordEncoder, jwtUtils,fileManager,authUserMailSender);
    }

    @Override
    @Transactional
    public LoginResponse login(KakaoUserRequest userRequest, String userAgent) {
        KakaoUserRequest kakaoUserRequest = new KakaoUserRequest(
           "Kakao@"+ userRequest.getUserId(), 
            userRequest.getNickName(), 
            userRequest.getUserPw(),
            userRequest.getUserEmail(),
            userRequest.getUserPwCnf(), 
            userAgent,
            userRequest.getSelectReception()
        );
        if(!idCheck(kakaoUserRequest.getUserId())){
            return doSocialSignUp(kakaoUserRequest);
        }else{
           return doLogin(kakaoUserRequest);
        }      
    }
    @Override
    public LoginResponse signUp(KakaoUserRequest userRequest) {;
        return null;
    }
}