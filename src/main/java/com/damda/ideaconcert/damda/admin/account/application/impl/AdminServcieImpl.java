package com.damda.ideaconcert.damda.admin.account.application.impl;

import java.util.Optional;

import com.damda.ideaconcert.damda.admin.account.application.AdminLoginResponse;
import com.damda.ideaconcert.damda.admin.account.application.AdminService;
import com.damda.ideaconcert.damda.admin.account.domain.Admin;
import com.damda.ideaconcert.damda.admin.account.domain.AdminRepository;
import com.damda.ideaconcert.damda.admin.account.representation.AdminLoginRequestDto;
import com.damda.ideaconcert.damda.user.application.JwtUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServcieImpl implements AdminService {
  private final AdminRepository adminRepository;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;

  private boolean passwordMatch(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  public Optional<Admin> getAdminInfoFromServer(String adminId) {
    return adminRepository.findByAdminId(adminId);
  }

  @Override
  public boolean adminInfoExistFromServer(AdminLoginRequestDto loginRequest) {
    Optional<Admin> adminInfo = getAdminInfoFromServer(
      loginRequest.getAdminId()
    );
    if (adminInfo.isPresent()) {
      return isValidAdmin(loginRequest, adminInfo);
    } else {
      return false;
    }
  }

  private boolean isValidAdmin(
    AdminLoginRequestDto loginRequest,
    Optional<Admin> adminInfo
  ) {
    String rawPassword = loginRequest.getAdminPw();
    String encodedPassword = adminInfo.get().getAdminPw();
    boolean isCorrect = passwordMatch(rawPassword, encodedPassword);
    return isCorrect ? true : false;
  }

  @Override
  public AdminLoginResponse login(AdminLoginRequestDto loginRequest) {
    return doLogin(loginRequest);
  }

  private AdminLoginResponse doLogin(AdminLoginRequestDto loginRequest) {
    Optional<Admin> adminInfo = adminRepository.findByAdminId(
      loginRequest.getAdminId()
    );
    String token = adminInfo.get().getToken();
    if (token == null || !jwtUtils.isValidToken(token)) {
      String accessToken = jwtUtils.createToken(
        adminInfo.get().getId().toString(),
        adminInfo.get().getNickName(),
        adminInfo.get().getAdminId(),
        adminInfo.get().getRole()
      );
      adminInfo.get().setToken(accessToken);
      adminRepository.save(adminInfo.get());

      return new AdminLoginResponse(accessToken, "로그인 성공");
    }
    return new AdminLoginResponse(token, "로그인 성공");
  }
}
