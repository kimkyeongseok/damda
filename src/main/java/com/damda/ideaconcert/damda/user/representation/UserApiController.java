package com.damda.ideaconcert.damda.user.representation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.damda.ideaconcert.damda.user.application.*;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import com.damda.ideaconcert.damda.utils.UserAgent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/user")
public class UserApiController {
    private final HttpServletRequest servletRequest;
    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final DamdaService damdaService;
    private final AppleService appleService;
    private final UserService userService;
    
    @GetMapping("/check/{userId}")
    public ResponseEntity<String> idCheck(@PathVariable("userId") String userId) {
        if (damdaService.idCheck(userId)) {
            return new ResponseEntity<>("이미 가입된 이메일 입니다.", HttpStatus.valueOf(409));
        } else {
            return new ResponseEntity<>("사용 가능한 이메일 입니다.", HttpStatus.OK);
        }
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<LoginResponse> loginKakao(@RequestBody KakaoUserRequest userRequest, HttpServletResponse servletResponse) {
        String userAgent = UserAgent.getUserAgent(servletRequest);
        LoginResponse loginResponse = kakaoService.login(userRequest,userAgent);
        createCookie(servletResponse, loginResponse);
        return new ResponseEntity<>(loginResponse, HttpStatus.valueOf(loginResponse.getCode()));
    }

    @PostMapping("/login/google")
    public ResponseEntity<LoginResponse> loginGoogle(@RequestBody GoogleUserRequest userRequest,HttpServletResponse servletResponse) {
        String userAgent = UserAgent.getUserAgent(servletRequest);
        LoginResponse loginResponse = googleService.login(userRequest, userAgent);
        createCookie(servletResponse,loginResponse);
        return new ResponseEntity<>(loginResponse, HttpStatus.valueOf(loginResponse.getCode()));
    }
    @PostMapping("/login/apple")
    public ResponseEntity<LoginResponse> loginGoogle(@RequestBody AppleUserRequest userRequest, HttpServletResponse servletResponse) {
        String userAgent = UserAgent.getUserAgent(servletRequest);
        LoginResponse loginResponse = appleService.login(userRequest, userAgent);
        createCookie(servletResponse,loginResponse);
        return new ResponseEntity<>(loginResponse, HttpStatus.valueOf(loginResponse.getCode()));
    }

    @PostMapping("/signup/damda")
    public ResponseEntity<SignUpResponse> signUpDamda(@Valid @RequestBody DamdaUserRequest userRequest) {
        SignUpResponse response = damdaService.damdaSignUp(userRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping("/login/damda")
    public ResponseEntity<LoginResponse> loginDamda(@RequestBody DamdaUserRequest userRequest,HttpServletResponse servletResponse) {
        String userAgent = UserAgent.getUserAgent(servletRequest);
        LoginResponse loginResponse = damdaService.login(userRequest, userAgent);
        createCookie(servletResponse,loginResponse);
        return new ResponseEntity<>(loginResponse, HttpStatus.valueOf(loginResponse.getCode()));
    }
    @PutMapping("/profile")
    public ResponseEntity<UserUpdateResponse> updateUserInfo(@ModelAttribute @Valid UserUpdateRequest request, @CookieValue("_dawt") String jwt) {
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        UserUpdateResponse response = damdaService.updateUserInfo(request, userPk);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    @GetMapping("/check")
    public ResponseEntity<String> userCheck() {
        return new ResponseEntity<>("인증된 사용자 입니다.", HttpStatus.OK);
    }
    private void createCookie(HttpServletResponse servletResponse, LoginResponse loginResponse) {
        ResponseCookie rc = ResponseCookie.from("_dawt", loginResponse.getAccessToken())
          .path("/")
          .httpOnly(true)
          .secure(true)
          // .sameSite("None")
          .build();
          servletResponse.addHeader("Set-Cookie", rc.toString());
    }
    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> userInfo(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        UserInfoDto info = userService.getUserInfo(userPk);
        return new ResponseEntity<>(info,HttpStatus.OK);
    }
}