package com.damda.ideaconcert.damda.user.application;

import com.damda.ideaconcert.damda.payment.application.UserPaymentInfoDto;
import com.damda.ideaconcert.damda.payment.domain.Payment;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserInfoDto getUserInfo(int userPk) {
        User userInfo = userRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException());

        UserInfoDto userInfoRequest =
                new UserInfoDto(
                        userInfo.getId(),
                        userInfo.getUserId(),
                        userInfo.getNickName(),
                        userInfo.getImgUrl(),
                        userInfo.getSnsUrl(),
                        userInfo.getUserEmail()
                );
        return userInfoRequest;
    }
}
