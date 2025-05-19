package com.damda.ideaconcert.damda.admin.user.application;

import java.util.List;

import com.damda.ideaconcert.damda.admin.user.representation.UserReadDto;

public interface AdminUserService {
    List<UserReadDto> read();
    void deactivate(int id);
	void active(int id);
    long total();
}
