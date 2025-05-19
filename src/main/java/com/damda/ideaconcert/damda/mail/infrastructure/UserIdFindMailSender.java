package com.damda.ideaconcert.damda.mail.infrastructure;

import com.damda.ideaconcert.damda.mail.application.AbstractMailService;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
public class UserIdFindMailSender extends AbstractMailService {

    public UserIdFindMailSender(
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
            User user = userRepository.findByUserIds(userId);
            sendMail = new MailUtils(mailSender);
            sendMail.setSubject("[Damda] 아이디 찾기 메일");
            sendMail.setTo(user.getUserEmail());
            Context context = new Context();
            context.setVariable("nickName", nickName);
            context.setVariable("key", authKey);
            context.setVariable("msg1", "회원님의 Damda 아이디 찾기 요청을 받았습니다.");
            context.setVariable("msg2", "회원님의 아이디는 "+ user.getUserId() + "입니다." );

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
