package com.damda.ideaconcert.damda.mail.application;

import com.damda.ideaconcert.damda.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractMailService {
    @Value("${damda.url}")
    public String DAMDA_URL;

    protected final JavaMailSender mailSender;
    protected final SpringTemplateEngine templateEngine;
    protected final UserRepository userRepository;
    public abstract void send(String userId, String authKey, String nickName);
}