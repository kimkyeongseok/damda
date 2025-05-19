package com.damda.ideaconcert.damda.helps.representation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.damda.ideaconcert.damda.helps.application.HelpsService;
import com.damda.ideaconcert.damda.user.application.DamdaUserRequest;
import com.damda.ideaconcert.damda.user.representation.ResetPasswordRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/helps")
@RequiredArgsConstructor
public class HelpsApiController {
    private final HelpsService helpsService;
    
    @PostMapping("/deactive")
    public ResponseEntity<AuthResponse> deactivateUserAccount(
            @CookieValue("_dawt") String jwt,
            HttpServletResponse servletResponse  
        ) {
        AuthResponse response = helpsService.deactivateUserAccount(jwt, servletResponse);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest userRequest){
        AuthResponse response =  helpsService.resetPassword(userRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    @GetMapping("/verify/{userId}/key/{key}/reset-password")
    public ModelAndView verifyUserForResetPassword(@PathVariable("userId") String userId, @PathVariable("key") String key) {
        boolean result =  helpsService.verifyEmailForResetPassword(userId, key);
        ModelAndView view = new ModelAndView();
        if(result) {
            view.setViewName("helps/reset_password");
            view.addObject("userId", userId);
        }else {
            view.addObject("status", "NOT FOUND");
            view.addObject("msg", "유효하지 않은 정보입니다.");
            view.setViewName("alert");
        }
        return view;
    }
    @PostMapping("/send/email/reset-password")
    public ResponseEntity<AuthResponse> sendEmailForResetPassword(@RequestBody DamdaUserRequest userRequest){
        AuthResponse response = helpsService.sendEmailForResetPassword(
            userRequest.getUserId(), 
            userRequest.getNickName()
        );
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/verify/{userId}/key/{key}")
    public ModelAndView verifyEmailForAuthUser(@PathVariable("userId") String userId,@PathVariable("key") String key){
        boolean result = helpsService.verifyEmailForAuthUser(userId, key);
        ModelAndView view = new ModelAndView();
        view.setViewName("alert");
        if(result) {
            view.addObject("status", "SUCCESS");
            view.addObject("msg", "이메일 인증이 완료되었습니다. 로그인 페이지로 돌아가 로그인해주세요");

        }else {
            view.addObject("status", "NOT FOUND");
            view.addObject("msg", "유효하지 않은 정보입니다.");
        }
        return view;
    }
    @GetMapping("/send/email/id-find/{userEmail}")
    public ResponseEntity<AuthResponse> sendEmailFindUserId(@PathVariable String userEmail){
        AuthResponse response = helpsService.sendEmailForFindUser(userEmail);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    
}
