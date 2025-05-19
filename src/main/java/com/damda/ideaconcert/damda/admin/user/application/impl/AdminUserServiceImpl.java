package com.damda.ideaconcert.damda.admin.user.application.impl;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.damda.ideaconcert.damda.admin.user.application.AdminUserService;
import com.damda.ideaconcert.damda.admin.user.representation.UserReadDto;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService{
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public List<UserReadDto> read() {
        List<User> userList = userRepository.findAll();
        List<UserReadDto> response = userList.stream().map(user -> {
            return new UserReadDto(
                user.getId(),
                user.getUserId(), 
                user.getNickName(), 
                user.getSignUpDate(),
                user.getLoginDate(),
                user.getUserAgent(),
                user.isDeactivate()
            );
        }).collect(Collectors.toList());
        return response;
    }

    @Override
    @Transactional
    public void deactivate(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        user.setDeactivate(true);
        user.setDeactivateDate(Instant.now());
    }

    @Override
    @Transactional
    public void active(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        user.setDeactivate(false);
        user.setDeactivateDate(null);
    }

    @Override
    public long total() {
        return userRepository.count();
    }

    
}
