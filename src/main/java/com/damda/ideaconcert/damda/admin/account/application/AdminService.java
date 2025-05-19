package com.damda.ideaconcert.damda.admin.account.application;

import com.damda.ideaconcert.damda.admin.account.representation.AdminLoginRequestDto;

public interface AdminService {
	AdminLoginResponse login(AdminLoginRequestDto loginRequest);
	boolean adminInfoExistFromServer(AdminLoginRequestDto loginRequest);
}
