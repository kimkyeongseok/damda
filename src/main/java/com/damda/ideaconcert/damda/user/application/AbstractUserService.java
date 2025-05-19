package com.damda.ideaconcert.damda.user.application;

import java.time.Instant;
import java.util.Optional;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
@Slf4j
@Service
@RequiredArgsConstructor
public abstract class AbstractUserService<T extends AbstractUserRequest> {
    protected final UserRepository userRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final JwtUtils jwtUtils;
    protected final FileManager fileManager;
    protected final AuthUserMailSender authUserMailSender;

    public abstract LoginResponse signUp(T t);
    public abstract LoginResponse login(T t, String userAgent);
    private String token;

    @Transactional
    public boolean idCheck(String userId) {
        Optional<User> userInfo = userRepository.findByUserId(userId);
        return userInfo.isPresent() ? true : false; 
     }

    @Transactional
    public void doDamdaSignUp(AbstractUserRequest userRequest){
        User user = User.builder()
                .userId(userRequest.getUserId())
                .nickName(userRequest.getNickName())
                .userPw(userRequest.getUserPw())
                .userEmail(userRequest.getUserEmail())
                .token("")
                .role("ROLE_USER")
                .isAuth(false)
                .signUpDate(Instant.now())
                .selectReception(userRequest.getSelectReception())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public LoginResponse doSocialSignUp(AbstractUserRequest userRequest) {
        User user = User.builder()
            .userId(userRequest.getUserId())
            .nickName(userRequest.getNickName())
            .userPw("")
            .role("ROLE_USER")
            .isAuth(true)
            .loginDate(Instant.now())
            .signUpDate(Instant.now())
            .userAgent(userRequest.getUserAgent())
            .build();
        user = userRepository.save(user);

        String token = createJWT(
            user.getId().toString(),
            userRequest.getUserId(),
            userRequest.getUserId(),
            user.getRole()
        );
        user = User.builder()
        .token(token)
        .build();
		return new LoginResponse(
            200,
            "signup success",
            "소셜 회원 가입에 성공하였습니다.",
            token
        );
    }
    
    @Transactional
    public LoginResponse doLogin(AbstractUserRequest userRequest) {
        Optional<User> userInfo = userRepository.findByUserId(userRequest.getUserId());
        if(userInfo.get().isDeactivate()){
            return new LoginResponse(
                403, 
                "login fail", 
                "비활성화된 유저입니다. 관리자에게 문의하세요", 
                null
            );
        }else {
            userInfo.ifPresent(loginUser -> {
                String currentToken = loginUser.getToken();
                if(currentToken == null || !jwtUtils.isValidToken(currentToken)) {
                    String accessToken = createJWT(
                        userInfo.get().getId().toString(),
                        userRequest.getNickName(),
                        userRequest.getUserId(),
                        userInfo.get().getRole()
                    );
                    token = accessToken;
                    loginUser.setToken(accessToken);       
                }else {
                    token = currentToken;
                }
                userInfo.get().login(token,userRequest.getUserAgent());
            });
            return new LoginResponse(
                    200, 
                    "login success",
                    "로그인 성공",
                    token
            );
        }
    }
        
    private String createJWT(String userPk, String nickName, String userId, String role) {
        return jwtUtils.createToken(userPk, nickName, userId, role);
    }

}