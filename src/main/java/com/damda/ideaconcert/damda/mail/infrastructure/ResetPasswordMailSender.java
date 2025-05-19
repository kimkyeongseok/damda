package com.damda.ideaconcert.damda.mail.infrastructure;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.damda.ideaconcert.damda.mail.application.AbstractMailService;

import com.damda.ideaconcert.damda.user.domain.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class ResetPasswordMailSender extends AbstractMailService {
    public ResetPasswordMailSender(
        JavaMailSender mailSender, 
        SpringTemplateEngine templateEngine,
        UserRepository userRepository
        ) {
        super(mailSender, templateEngine,userRepository);
    }

    @Override
    public void send(String userId, String authKey, String nickName) {
        MailUtils sendMail;
        try {
            sendMail = new MailUtils(mailSender);
            sendMail.setSubject("[Damda] 비밀번호 재설정 메일");
            sendMail.setTo(userId);
            Context context = new Context();
            context.setVariable("nickName", nickName);
            context.setVariable("key", authKey);
            context.setVariable("msg1", "회원님의 Damda 비밀번호 재설정 요청을 받았습니다.");
            context.setVariable("msg2", "비밀번호 재설정 창이 열립니다.");
            context.setVariable(
                "url", 
                DAMDA_URL+"/api/helps/verify/"+userId+"/key/"+authKey+"/reset-password"
                );
            context.setVariable("buttonText", "비밀번호 재설정");
            
            String html = templateEngine.process("mail_template", context);
            sendMail.setText(html);
            sendMail.setFrom("idea.hidamda@gmail.com", "Damda");
            sendMail.send();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
