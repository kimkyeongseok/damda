package com.damda.ideaconcert.damda.admin.account.representation;

import javax.servlet.http.HttpServletResponse;

import com.damda.ideaconcert.damda.admin.account.application.AdminLoginResponse;
import com.damda.ideaconcert.damda.admin.account.application.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {
    private final AdminService adminService;
    
    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@RequestBody AdminLoginRequestDto loginRequestDto, HttpServletResponse servletResponse) {
        AdminLoginRequestDto loginRequest = new AdminLoginRequestDto(
            loginRequestDto.getAdminId(),
            loginRequestDto.getAdminPw()
        );
        
        if(adminService.adminInfoExistFromServer(loginRequest)){
            AdminLoginResponse loginResponse = adminService.login(loginRequest);
            createCookie(servletResponse,loginResponse);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }else{
            AdminLoginResponse loginResponse = new AdminLoginResponse(
                    null, 
                    "가입하지 않은 아이디이거나 잘못된 비밀번호 입니다."
                );
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/logout")
    public void adminLogout(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("_dawt", "")
            .path("/")
            .httpOnly(true)
            .maxAge(0)
            .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
    private void createCookie(HttpServletResponse servletResponse, AdminLoginResponse loginResponse) {
        ResponseCookie rc = ResponseCookie.from("_dawt", loginResponse.getAccessToken())
          .path("/")
          .httpOnly(true)
          .secure(true)
          // .sameSite("None")
          .build();
          servletResponse.addHeader("Set-Cookie", rc.toString());
    }
}
