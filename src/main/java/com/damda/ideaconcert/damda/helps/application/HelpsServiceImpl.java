package com.damda.ideaconcert.damda.helps.application;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.damda.ideaconcert.damda.authkey.application.AuthKeyStorage;
import com.damda.ideaconcert.damda.authkey.application.AuthKeyStorageRequest;
import com.damda.ideaconcert.damda.authkey.application.AuthKeyStorageRequest.Type;
import com.damda.ideaconcert.damda.helps.representation.AuthResponse;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.mail.infrastructure.ResetPasswordMailSender;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;
import com.damda.ideaconcert.damda.user.representation.ResetPasswordRequest;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import com.damda.ideaconcert.damda.utils.NameUtils;

import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpsServiceImpl implements HelpsService {
    private final AuthKeyStorage authKeyStorage;
    private final AuthUserMailSender authUserMailSender;
    private final ResetPasswordMailSender resetPasswordMailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // private final PostService postService;
    // private final PostRepository postRepository;
    
    @Override
    @Transactional
    public LoginResponse sendEmailForAuthUser(String userId, String nickName) {
        String authKey = NameUtils.createUniqueName();
        AuthKeyStorageRequest request = new AuthKeyStorageRequest(
            Type.AUTH_EMAIL, 
            userId, 
            authKey
        );
        if(authKeyStorage.read(request) != null) {
             return new LoginResponse(
                409, 
                "", 
                "이미 인증 메일을 발송했습니다. 메일함에서 확인해 주세요.", 
                null
            );
        } else {
            authKeyStorage.create(request);
            authUserMailSender.send(userId, authKey, nickName);
            Optional<User> loginUser = userRepository.findByUserId(userId);
            loginUser.ifPresent(user -> {
                user.setAuthKey(authKey);
            });
            return new LoginResponse(
                403, 
                "", 
                "인증 메일을 발송했습니다. 가입 시 입력한 이메일로 인증해주세요.", 
                null
            );
        }
    }

    public AuthResponse sendEmailForFindUser(String userEmail) {
        String authKey = NameUtils.createUniqueName();

        User user = userRepository.findByUserEmail(userEmail);
        if(user != null) {
            authUserMailSender.send(user.getUserId(),authKey,user.getNickName());
            return new AuthResponse(
                    200,
                    "인증 메일로 아이디를 보냈습니다."
            );
        } else {
            return new AuthResponse(
                    400,
                    "가입된 사용자 인증 메일 없습니다. 확인부탁드립니다"
            );
        }
    }


    @Override
    @Transactional
    public boolean verifyEmailForAuthUser(String userId, String authKey) {
        Optional<User> loginUser = userRepository.findByUserId(userId);
        // TODO 안티 패턴임 ifPresentOrElse 등으로 처리할것
        if(loginUser.isPresent()){
            String currentAuthKey = loginUser.get().getAuthKey();
            if(currentAuthKey.equals(authKey)){
                loginUser.get().setAuth(true);
                loginUser.get().setAuthKey("");
                AuthKeyStorageRequest request = new AuthKeyStorageRequest(
                    Type.AUTH_EMAIL, 
                    userId, 
                    null
                );
                authKeyStorage.delete(request);
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public AuthResponse sendEmailForResetPassword(String userId, String nickName) {
        Optional<User> userInfo = userRepository.findByUserId(userId);
        User user = userRepository.findByUserIds(userId);
        if(userInfo.isPresent()){
            String authKey = NameUtils.createUniqueName();
            AuthKeyStorageRequest request = new AuthKeyStorageRequest(
                Type.RESET_PASSWORD, 
                userId, 
                authKey
            );
            if(authKeyStorage.read(request) != null) {
                return new AuthResponse(200, "이미 이메일을 발송했습니다. 메일함에서 확인 해주세요.");
            } else {
                authKeyStorage.create(request);
                resetPasswordMailSender.send(userId, authKey, user.getNickName());
                return new AuthResponse(200, "해당 이메일로 비밀번호 재설정 링크를 전송했습니다.");
            }
          
        }else {
            return new AuthResponse(400, "회원정보가 일치하지 않습니다. 다시 입력해주세요.");
        }
    }

    @Override
    public boolean verifyEmailForResetPassword(String userId, String authKey) {
        AuthKeyStorageRequest request = new AuthKeyStorageRequest(
            Type.RESET_PASSWORD, 
            userId, 
            authKey
        );
        String currentKey = authKeyStorage.read(request);
        if(currentKey != null){
            boolean result = currentKey.equals(authKey) ? true : false;
            return result;
        }
        return false;
    }

    @Override
    @Transactional
    public AuthResponse resetPassword(@Valid ResetPasswordRequest userRequest) {
        Optional<User> user = userRepository.findByUserId(userRequest.getUserId());
        if (!userRequest.getUserPw().equals(userRequest.getUserPwCnf())) {
            return new AuthResponse(401, "비밀번호가 일치하지 않습니다.");
        }else {
            AuthKeyStorageRequest request = new AuthKeyStorageRequest(
                Type.RESET_PASSWORD, 
                userRequest.getUserId(), 
                null
            );
            authKeyStorage.delete(request);
            user.ifPresent(userInfo -> {
                userInfo.setUserPw(passwordEncoder.encode(userRequest.getUserPw()));
            });
        }
        return new AuthResponse(200, "비밀번호가 변경되었습니다.");
    }

    @Override
    @Transactional
    public AuthResponse deactivateUserAccount(String jwt, HttpServletResponse servletResponse) {
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        String userId = userInfo.get("userId").toString();
        // int userPk = Integer.parseInt(userInfo.get("aud").toString());
        Optional<User> user= userRepository.findByUserId(userId);

        if(user.isPresent()){
            // 등록한 게시글 삭제 메소드.. 필요하다면 넣으시면 됩니다.
            // List<Post> posts = postRepository.findByUserPk(userPk);
            // posts.stream().forEach(it -> {
            //     postService.delete(it.getId(), userPk);
            // });
            // TODO 결제기록 5년 보관 후 삭제, 회원 정보 삭제 메소드 따로 필요(파기정책상)
            user.get().setDeactivate(true);
            user.get().setDeactivateDate(Instant.now());
      
            ResponseCookie cookie = ResponseCookie.from("_dawt", "")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(0)
                    .build();
            servletResponse.addHeader("Set-Cookie", cookie.toString());
            return new AuthResponse(200, "탈퇴가 완료되었습니다.");
        }
        return null;
    }
    
}
