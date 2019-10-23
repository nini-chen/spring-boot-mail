package com.imooc.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to,String subject,String context){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);//发件人
        mailMessage.setTo(to);//收件人
        mailMessage.setSubject(subject);//主题
        mailMessage.setText(context);//邮件内容
        mailSender.send(mailMessage);//发送
    }
}
